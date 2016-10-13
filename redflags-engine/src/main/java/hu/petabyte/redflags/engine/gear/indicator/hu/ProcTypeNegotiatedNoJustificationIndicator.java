package hu.petabyte.redflags.engine.gear.indicator.hu;

import hu.petabyte.redflags.engine.gear.indicator.AbstractTD3CIndicator;
import hu.petabyte.redflags.engine.gear.indicator.helper.DirectiveHelper;
import hu.petabyte.redflags.engine.gear.indicator.helper.ProfilesHelper;
import hu.petabyte.redflags.engine.model.IndicatorResult;
import hu.petabyte.redflags.engine.model.Notice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Zsolt Jurányi
 */
@Component
@ConfigurationProperties(prefix = "procTypeNegotiatedNoJustificationIndicator")
public class ProcTypeNegotiatedNoJustificationIndicator extends
AbstractTD3CIndicator {

	private static final Logger LOG = LoggerFactory
			.getLogger(ProcTypeNegotiatedNoJustificationIndicator.class);
	private @Autowired ProfilesHelper profiles;

	@Override
	protected IndicatorResult flagImpl(Notice notice) {
		if (DirectiveHelper.isPublicProcurementDirective(notice)
				&& !profiles.isTestProfile()) {
			LOG.warn(
					"Skipping notice {}, it's public procurement directive and this case is not implemented.",
					notice.getId());
			return irrelevantData();
		}

		String procType = fetchProcedureType(notice);
		if (!procType.matches("PR-4")) {
			return irrelevantData();
		}

		String s = fetchAdditionalInfo(notice);
		for (String line : s.split("\n")) {
			if (line.matches(".*89.*?(\\(2\\))?.*?[a-d][\\),].*")
					|| line.matches(".*([Ee]ljárás|[Tt]árgyalás)( alkalmazásának)? indok.*")
					|| line.matches(/* a) */".*eredménytelen.*feltételek időközben lényegesen nem változtak meg.*")
					|| line.matches(
							/* b) */".*objektív természete.*kockázatok.*ellenszolgáltatás( előzetes)?.*meghatározás.*")
					|| line.matches(
									/* c) */".*kutatási.*kísérleti.*fejlesztési.*piacképesség.*kutatásfejlesztés költség.*")
					|| line.matches(
											/* d) */".*nem (határozható|lehetséges).*?(olyan|kell).*?(pontossággal|részletességgel).*")) {
				return null;
			}
		}

		return returnFlag();
	}
}
