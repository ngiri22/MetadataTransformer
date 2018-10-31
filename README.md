# MetadataTransformer

- Create the jar file out of the source code.
- Place the jar file in <TEAMS_HOME>/ear/artesia/lib folder.
- Make the property ASSET_INTERCEPTOR_CLASS "Active" from the OTMM Admins Settings Screen.
- Value of the property ASSET_INTERCEPTOR_CLASS should be set to  "com.nttdata.lumileds.opentext.transformer.MetadataTransformer"
- Restart the OTMM application.

Any asset imported into OTMM would be now applied with the extra metadata properties from PAL Metadata.

# Assets Seggregator

- Seggregates assets from an input/SOURCE folder into PCC, MARCOM, PCC/Regional Identification, MARCOM/Regional Identification and AM/Undefined folders.
- Create following folders inside the base folder:
	- SOURCE (Will have source assets)
	- PCC (pcc assets will be copied here).
		--> Regional Specification
	- MARCOM
		--> Regional Specification
- Call the below java command.
java -cp sqljdbc42.jar;MetadataTransformer.jar com.nttdata.lumileds.opentext.transformer.utility.PALAssetSeggregator "<BASE_FOLDER_LOCATION>"
- This command will read from input folder and seggregate the assets into the different folders based on the Asset Taxonomy.
