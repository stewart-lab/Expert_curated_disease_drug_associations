# **Expert curated disease-drug associations (DDA)**   

**Resources:**   
 1. Comparative Toxicogenomics Database (CTD)
 2. National Drug File - Reference Terminology (NDF-RT)
 3. DrugBank
 4. ClinicalKey
   
Introduction: Disease2DrugAssociationGoldStandard project is for extracting disease-drug associations from four resources, Comparative Toxicogenomics Database (CTD, National Drug File-Reference Terminology (NDF-RT), DrugBank, and ClinicalKey.   
  
Prerequisites: The resource files should be downloaded from CTD, NDF-RT and DrugBank. While CTD and DrugBank provide free access to download the entire data, downloading the data from NDF-RT bioportal is challenging. Alternatively, we can download NDF-RT data available within UMLS Metathesaurus, a collection of biomedical concepts from around 200 dictionaries. There is no option to download the data from ClinicalKey. For diseases of interest, we collected the annotated drugs from ClinicalKey. In addition to the resource files, this project requires UMLS Metathesaurus to map unique concept identifier (CUI) to disease name and our own chemicals/drugs dictionary to map the customized ID to chemical/drug name. Downloading and installation of UMLS Metathesaurus requires license (https://www.nlm.nih.gov/research/umls/knowledge_sources/metathesaurus/). Please refer to https://github.com/CutaneousBioinf/LiteratureMiningTool/tree/master/ConceptMap/ for processing of UMLS Metathesaurus. Chemicals/drugs lexicon is compiled from three resources namely UMLS Metathesaurus, DrugBank and PharmGKB. Please refer to https://github.com/CutaneousBioinf/LiteratureMiningTool/tree/master/DrugDict/.
  
---- RUN IN AN IDE ----  
The entire project should be pulled into Java IDE, such as eclipse. The execution of the project starts from main method within each package and documented clearly.  
  
---- COMPILE AND RUN ON THE COMMAND LINE ----  
Processing includes Java programs and Linux commands. Please see https://github.com/stewart-lab/Expert_curated_disease_drug_associations/tree/master/Documentation/Expert_curated_DDA.docx.  

Java version used for development: JavaSE-1.8   

Author: Kalpana Raja   

Affiliation: University of Michigan, Ann arbor, MI, USA and Morgridge Institute for Research, Madison, WI, USA
  
Author is currently at Morgridge Institute for Research, Madison, WI, USA
