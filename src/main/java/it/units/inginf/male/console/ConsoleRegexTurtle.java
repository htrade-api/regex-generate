package it.units.inginf.male.console;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import it.units.inginf.male.configuration.Configuration;
import it.units.inginf.male.dto.SimpleConfig;
import it.units.inginf.male.inputs.DataSet;
import it.units.inginf.male.inputs.DataSet.Example;
import it.units.inginf.male.outputs.FinalSolution;
import it.units.inginf.male.outputs.Results;
import it.units.inginf.male.postprocessing.JsonPostProcessor;
import it.units.inginf.male.strategy.ExecutionStrategy;
import it.units.inginf.male.strategy.impl.CoolTextualExecutionListener;
import it.units.inginf.male.utils.Utils;

public class ConsoleRegexTurtle {
	private static String WARNING_MESSAGE = "\nWARNING\nThe quality of the solution depends on a number of factors, including size and syntactical properties of the learning information.\nThe algorithms embedded in this experimental prototype have always been tested with at least 25 matches over at least 2 examples.\nIt is very unlikely that a smaller number of matches allows obtaining a useful solution.\n";
	private static final String HELP_MESSAGE = "Usage:\njava -jar ConsoleRegexTurtle -t 4 -p 500 -g 1000 -e 20.0 -c \"interesting evolution\" -x true -d dataset.json -o ./outputfolder/\n\nOn linux you can invoke this tool using the alternative script:\nregexturtle.sh -t 4 -p 500 -g 1000 -e 20.0 -c \"interesting evolution\" -d dataset.json -o ./outputfolder/\n\nParameters:\n-t number of threads, default is 2\n-p population size, default is 500\n-g maximum number of generations, per Job, default si 1000\n-j number of Jobs, default si 32\n-e percentange of number generations, defines a threshold for the separate and conquer split criteria, when best doesn't change for the provided % of generation the Job evolution separates the dataset.\n   Default is 20%, 200 geberations with default 1000 generations.\n-d path of the dataset json file containing the examples, this parameter is mandatory.\n-o name of the output folder, results.json is saved into this folder; default is '.'\n-x boolean, populates an extra field in results file, when 'true' adds all dataset examples in the results file 'examples' field, default is 'false'\n-s boolean, when 'true' enables dataset striping, striping is an experimental feature, default is disabled: 'false'\n-c adds an optional comment string\n-h visualizes this help message\n";

	public static void getRegex(String datajson, String key, FirebaseDatabase defaultDatabase) {

		SimpleConfig simpleConfiguration = new SimpleConfig();

		simpleConfiguration.outputFolder = ".";

		simpleConfiguration.numberOfJobs = 10;//32;
		simpleConfiguration.generations = 1000;
		simpleConfiguration.numberThreads = 4;
		simpleConfiguration.populationSize = 500;
		simpleConfiguration.termination = 20.0D;
		simpleConfiguration.populateOptionalFields = false;
		simpleConfiguration.isStriped = false;

		// parseArgs(args, simpleConfiguration);

		simpleConfiguration.dataset = loadDatasetJson(datajson);

		String message = null;
		int numberPositiveExamples = 0;
		Iterator i$ = simpleConfiguration.dataset.getExamples().iterator();
		while (i$.hasNext()) {
			Example example = (Example) i$.next();
			if (example.getNumberMatches() > 0) {
				++numberPositiveExamples;
			}
		}

//		if (simpleConfiguration.dataset.getNumberMatches() < 25 || numberPositiveExamples < 2) {
//			message = WARNING_MESSAGE;
//		}

		Configuration config = simpleConfiguration.buildConfiguration();

		config.setPostProcessor(new JsonPostProcessor());
		config.getPostprocessorParameters().put("populateOptionalFields",
				Boolean.toString(simpleConfiguration.populateOptionalFields));
		config.setOutputFolderName(simpleConfiguration.outputFolder);

		Results results = new Results(config);
		results.setComment(simpleConfiguration.comment);

		try {
			results.setMachineHardwareSpecifications(Utils.cpuInfo());
		} catch (IOException var12) {
			Logger.getLogger(ConsoleRegexTurtle.class.getName()).log(Level.SEVERE, (String) null, var12);
		}
		CoolTextualExecutionListener consolelistener = new CoolTextualExecutionListener(message, config, results, key, defaultDatabase);

		long startTime = System.currentTimeMillis();
		ExecutionStrategy strategy = config.getStrategy();
		try {
			strategy.execute(config, consolelistener);
		} catch (Exception var11) {
			Logger.getLogger(ConsoleRegexTurtle.class.getName()).log(Level.SEVERE, (String) null, var11);
		}

		if (config.getPostProcessor() != null) {
			startTime = System.currentTimeMillis() - startTime;
			config.getPostProcessor().elaborate(config, results, startTime);
		}

	}

	private static DataSet loadDataset(String dataSetFilename) throws IOException {

		FileInputStream fis = new FileInputStream(new File(dataSetFilename));
		InputStreamReader isr = new InputStreamReader(fis);
		BufferedReader bufferedReader = new BufferedReader(isr);

		StringBuilder sb = new StringBuilder();
		String line;

		while((line = bufferedReader.readLine()) != null) {
			sb.append(line);
		}

		String json = sb.toString();
		return loadDatasetJson(json);
	}


	private static DataSet loadDatasetJson(String jsonDataset) {
       Gson gson = (new GsonBuilder()).disableHtmlEscaping().create();
       DataSet dataset = (DataSet)gson.fromJson(jsonDataset, DataSet.class);
       return dataset;
    }

	private static void writeBestPerformances(FinalSolution solution) {
       if (solution != null) {
          System.out.println("Best on learning (JAVA): " + solution.getSolution());
          System.out.println("Best on learning (JS): " + solution.getSolutionJS());
          System.out.println("******Stats on training******");
          System.out.println("F-measure: " + solution.getTrainingPerformances().get("match f-measure"));
          System.out.println("Precision: " + solution.getTrainingPerformances().get("match precision"));
          System.out.println("Recall: " + solution.getTrainingPerformances().get("match recall"));
          System.out.println("Char precision: " + solution.getTrainingPerformances().get("character precision"));
          System.out.println("Char recall: " + solution.getTrainingPerformances().get("character recall"));
          System.out.println("******Stats on validation******");
          System.out.println("F-measure " + solution.getValidationPerformances().get("match f-measure"));
          System.out.println("Precision: " + solution.getValidationPerformances().get("match precision"));
          System.out.println("Recall: " + solution.getValidationPerformances().get("match recall"));
          System.out.println("Char precision: " + solution.getValidationPerformances().get("character precision"));
          System.out.println("Char recall: " + solution.getValidationPerformances().get("character recall"));
          System.out.println("******Stats on learning******");
          System.out.println("F-measure: " + solution.getLearningPerformances().get("match f-measure"));
          System.out.println("Precision: " + solution.getLearningPerformances().get("match precision"));
          System.out.println("Recall: " + solution.getLearningPerformances().get("match recall"));
          System.out.println("Char precision: " + solution.getLearningPerformances().get("character precision"));
          System.out.println("Char recall: " + solution.getLearningPerformances().get("character recall"));
       }
    }

	private static void parseArgs(String[] args, SimpleConfig simpleConfig) {
       try {
          boolean mandatoryDatasetCheck = true;
          if (args.length == 0) {
             System.out.println("Usage:\njava -jar ConsoleRegexTurtle -t 4 -p 500 -g 1000 -e 20.0 -c \"interesting evolution\" -x true -d dataset.json -o ./outputfolder/\n\nOn linux you can invoke this tool using the alternative script:\nregexturtle.sh -t 4 -p 500 -g 1000 -e 20.0 -c \"interesting evolution\" -d dataset.json -o ./outputfolder/\n\nParameters:\n-t number of threads, default is 2\n-p population size, default is 500\n-g maximum number of generations, per Job, default si 1000\n-j number of Jobs, default si 32\n-e percentange of number generations, defines a threshold for the separate and conquer split criteria, when best doesn't change for the provided % of generation the Job evolution separates the dataset.\n   Default is 20%, 200 geberations with default 1000 generations.\n-d path of the dataset json file containing the examples, this parameter is mandatory.\n-o name of the output folder, results.json is saved into this folder; default is '.'\n-x boolean, populates an extra field in results file, when 'true' adds all dataset examples in the results file 'examples' field, default is 'false'\n-s boolean, when 'true' enables dataset striping, striping is an experimental feature, default is disabled: 'false'\n-c adds an optional comment string\n-h visualizes this help message\n");
          }
          for(int i = 0; i < args.length; ++i) {
             String string = args[i];
             ++i;
             String parameter = args[i];            byte var7 = -1;            switch(string.hashCode()) {            case 1494:               if (string.equals("-c")) {                  var7 = 9;               }               break;            case 1495:               if (string.equals("-d")) {                  var7 = 2;               }               break;            case 1496:               if (string.equals("-e")) {
                   var7 = 6;               }            case 1497:            case 1500:            case 1502:            case 1503:            case 1504:            case 1505:            case 1508:            case 1509:            case 1512:            case 1513:            case 1514:            default:               break;            case 1498:               if (string.equals("-g")) {                  var7 = 4;               }               break;            case 1499:               if (string.equals("-h")) {                  var7 = 8;               }               break;            case 1501:               if (string.equals("-j")) {                  var7 = 5;               }               break;            case 1506:               if (string.equals("-o")) {                  var7 = 3;               }               break;            case 1507:               if (string.equals("-p")) {                  var7 = 1;               }               break;            case 1510:               if (string.equals("-s")) {                  var7 = 10;               }               break;            case 1511:               if (string.equals("-t")) {                  var7 = 0;               }               break;            case 1515:               if (string.equals("-x")) {                  var7 = 7;               }            }            switch(var7) {            case 0:
                simpleConfig.numberThreads = Integer.valueOf(parameter);               break;            case 1:
                simpleConfig.populationSize = Integer.valueOf(parameter);               break;            case 2:
                simpleConfig.datasetName = parameter;
                mandatoryDatasetCheck = false;
                break;            case 3:
                simpleConfig.outputFolder = parameter;               break;            case 4:
                simpleConfig.generations = Integer.valueOf(parameter);               break;            case 5:
                simpleConfig.numberOfJobs = Integer.valueOf(parameter);               break;            case 6:
                simpleConfig.termination = Double.valueOf(parameter);               break;            case 7:
                simpleConfig.populateOptionalFields = Boolean.valueOf(parameter);               break;            case 8:
                System.out.println("Usage:\njava -jar ConsoleRegexTurtle -t 4 -p 500 -g 1000 -e 20.0 -c \"interesting evolution\" -x true -d dataset.json -o ./outputfolder/\n\nOn linux you can invoke this tool using the alternative script:\nregexturtle.sh -t 4 -p 500 -g 1000 -e 20.0 -c \"interesting evolution\" -d dataset.json -o ./outputfolder/\n\nParameters:\n-t number of threads, default is 2\n-p population size, default is 500\n-g maximum number of generations, per Job, default si 1000\n-j number of Jobs, default si 32\n-e percentange of number generations, defines a threshold for the separate and conquer split criteria, when best doesn't change for the provided % of generation the Job evolution separates the dataset.\n   Default is 20%, 200 geberations with default 1000 generations.\n-d path of the dataset json file containing the examples, this parameter is mandatory.\n-o name of the output folder, results.json is saved into this folder; default is '.'\n-x boolean, populates an extra field in results file, when 'true' adds all dataset examples in the results file 'examples' field, default is 'false'\n-s boolean, when 'true' enables dataset striping, striping is an experimental feature, default is disabled: 'false'\n-c adds an optional comment string\n-h visualizes this help message\n");               break;            case 9:
                simpleConfig.comment = parameter;               break;            case 10:
                simpleConfig.isStriped = Boolean.valueOf(parameter);
             }
          }

          if (mandatoryDatasetCheck) {
             System.out.println("Dataset path is needed.\nUsage:\njava -jar ConsoleRegexTurtle -t 4 -p 500 -g 1000 -e 20.0 -c \"interesting evolution\" -x true -d dataset.json -o ./outputfolder/\n\nOn linux you can invoke this tool using the alternative script:\nregexturtle.sh -t 4 -p 500 -g 1000 -e 20.0 -c \"interesting evolution\" -d dataset.json -o ./outputfolder/\n\nParameters:\n-t number of threads, default is 2\n-p population size, default is 500\n-g maximum number of generations, per Job, default si 1000\n-j number of Jobs, default si 32\n-e percentange of number generations, defines a threshold for the separate and conquer split criteria, when best doesn't change for the provided % of generation the Job evolution separates the dataset.\n   Default is 20%, 200 geberations with default 1000 generations.\n-d path of the dataset json file containing the examples, this parameter is mandatory.\n-o name of the output folder, results.json is saved into this folder; default is '.'\n-x boolean, populates an extra field in results file, when 'true' adds all dataset examples in the results file 'examples' field, default is 'false'\n-s boolean, when 'true' enables dataset striping, striping is an experimental feature, default is disabled: 'false'\n-c adds an optional comment string\n-h visualizes this help message\n");
             System.exit(1);
          }
       } catch (RuntimeException var8) {
          System.out.println("Problem parsing commandline parameters.\nUsage:\njava -jar ConsoleRegexTurtle -t 4 -p 500 -g 1000 -e 20.0 -c \"interesting evolution\" -x true -d dataset.json -o ./outputfolder/\n\nOn linux you can invoke this tool using the alternative script:\nregexturtle.sh -t 4 -p 500 -g 1000 -e 20.0 -c \"interesting evolution\" -d dataset.json -o ./outputfolder/\n\nParameters:\n-t number of threads, default is 2\n-p population size, default is 500\n-g maximum number of generations, per Job, default si 1000\n-j number of Jobs, default si 32\n-e percentange of number generations, defines a threshold for the separate and conquer split criteria, when best doesn't change for the provided % of generation the Job evolution separates the dataset.\n   Default is 20%, 200 geberations with default 1000 generations.\n-d path of the dataset json file containing the examples, this parameter is mandatory.\n-o name of the output folder, results.json is saved into this folder; default is '.'\n-x boolean, populates an extra field in results file, when 'true' adds all dataset examples in the results file 'examples' field, default is 'false'\n-s boolean, when 'true' enables dataset striping, striping is an experimental feature, default is disabled: 'false'\n-c adds an optional comment string\n-h visualizes this help message\n");
          System.out.println("Error details:" + var8.toString());
          System.exit(1);
       }

    }
}

/*
	DECOMPILATION REPORT

	Decompiled from: C:\ftth\pleiades_up\workspace\ConsoleRegexTurtle\ImportedClasses/it/units/inginf/male/console/ConsoleRegexTurtle.class
	Total time: 245 ms

	Decompiled with FernFlower.
*/