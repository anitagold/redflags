package hu.petabyte.redflags;

import com.ibm.icu.text.PluralRules;
import org.junit.Test;

import java.util.Locale;

/**
 * @author Zsolt Jurányi
 */
public class PluralKeywords {

	@Test
	public void listKeywords() {
		String[] langs = {"en_GB", "hu_HU", "pl_PL"};
		for (String lang : langs) {
			System.out.println(lang + ": " + PluralRules.forLocale(new Locale(lang)).getKeywords());
		}
	}
}
