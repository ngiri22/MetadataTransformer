# MetadataTransformer

- Create the jar file out of the source code.
- Place the jar file in <TEAMS_HOME>/ear/artesia/lib folder.
- Make the property ASSET_INTERCEPTOR_CLASS "Active" from the OTMM Admins Settings Screen.
- Value of the property ASSET_INTERCEPTOR_CLASS should be set to  "com.nttdata.lumileds.opentext.transformer.MetadataTransformer"
- Restart the OTMM application.

Any asset imported into OTMM would be now applied with the extra metadata properties from PAL Metadata.
