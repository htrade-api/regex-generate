package it.units.inginf.male.dto;

import java.util.logging.Logger;

import it.units.inginf.male.configuration.Configuration;
import it.units.inginf.male.configuration.DatasetContainer;
import it.units.inginf.male.inputs.DataSet;

public class SimpleConfig {
	private final transient double STRIPING_THREASHOLD_CHAR_RATIO = 200.0D;
	private final transient double STRIPING_DEFAULT_MARGIN_SIZE = 10.0D;
	public int numberThreads;
	public int numberOfJobs;
	public int generations;
	public int populationSize;
	public DataSet dataset;
	public boolean populateOptionalFields;
	public boolean isStriped = false;
	public transient String datasetName;
	public transient String outputFolder;
	public double termination = 20.0D;
	public String comment;

	public Configuration buildConfiguration() {
		Configuration configuration = new Configuration();
		configuration.setConfigName("Console config");
		configuration.getEvolutionParameters().setGenerations(this.generations);
		configuration.getEvolutionParameters().setPopulationSize(this.populationSize);
		configuration.setJobs(this.numberOfJobs);
		configuration.getStrategyParameters().put("threads", String.valueOf(this.numberThreads));
		int terminationGenerations = (int) (this.termination
				* (double) configuration.getEvolutionParameters().getGenerations() / 100.0D);
		if (this.termination == 100.0D) {
			configuration.getStrategyParameters().put("terminationCriteria", "false");
		} else {
			configuration.getStrategyParameters().put("terminationCriteria", "true");
		}
		configuration.getStrategyParameters().put("terminationCriteriaGenerations",
				String.valueOf(terminationGenerations));

		configuration.getStrategyParameters().put("terminationCriteria2", "false");

		if (this.dataset == null) {
			throw new IllegalArgumentException("You must define a dataset");
		} else {
			this.dataset.populateUnmatchesFromMatches();
			DatasetContainer datasetContainer = new DatasetContainer(this.dataset);
			datasetContainer.createDefaultRanges((int) configuration.getInitialSeed());

			this.dataset.updateStats();
			if (this.isStriped) {
				Logger.getLogger(this.getClass().getName()).info("Enabled striping.");
				datasetContainer.setDataSetsStriped(true);
				datasetContainer.setDatasetStripeMarginSize(10.0D);
				datasetContainer.setProposedNormalDatasetInterval(100);
			}
			configuration.setDatasetContainer(datasetContainer);

			configuration.setup();

			return configuration;
		}
	}
}

/*
	DECOMPILATION REPORT

	Decompiled from: C:\ftth\pleiades_up\workspace\ConsoleRegexTurtle\ImportedClasses/it/units/inginf/male/dto/SimpleConfig.class
	Total time: 17 ms

	Decompiled with FernFlower.
*/