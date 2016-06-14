# Downloader gears in RED FLAGS engine



**Package:** `hu.petabyte.redflags.gear.archiver`



## Archiver



Takes an empty `Notice` and **downloads all available tabs** from *TED* using `TedInterface` ([details](/developer/engine/tedinterface/)).

*Depends on:*

* `TedInterfaceHolder` - uses this to download HTML pages and save them to cache
* `DocFamilyFetcher` - requires information generated by this gear

*Parameters:*

||
-------------------------------------|--------|---
`redflags.engine.gear.archive.langs` | String | A list of valid `DisplayLanguage` values. Items can be separated by any non-alpha characters. `Archiver` will download all available tabs for all given display languages.

*Before session:*

* should be called, it will parse the above parameter into `List<DisplayLanguage>` to be used by `process`.



## DocFamilyArchiver



Goes through the document family members (`Notice` objects) stored in the given `Notice` and calls `Archiver` for all of them. `DocFamilyFetcher` gear should be called before to parse family members.

*Depends on:*

* `Archiver` - calls this to download all tabs for family member notices