/*
 * Copyright (C) 2015 Machine Learning Lab - University of Trieste,
 * Italy (http://machinelearning.inginf.units.it/)
 *
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package it.units.inginf.male.postprocessing;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import it.units.inginf.male.configuration.Configuration;
import it.units.inginf.male.outputs.Results;
import it.units.inginf.male.outputs.gson.DoubleTypeAdapter;

/**
 * extends the BasicPostProcessor but saves the result.json file.
 * @author nvhuy9527
 */
public class JsonPostProcessor extends BasicPostprocessor {

    @Override
    public void setup(Map<String, String> parameters) {
    }

    @Override
    public void elaborate(Configuration config, Results results, long timeTaken) {
        super.elaborate(config, results, timeTaken);
        //saving results
        System.out.println("Saving results...");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
        String dateFormatted = sdf.format(new Date());
        String pathOfFile = config.getOutputFolder().getAbsolutePath() + File.separator + "results-"+dateFormatted+".json";
       // saveToJson(results, pathOfFile);

        String time = String.format("%d h, %d m, %d s",
                TimeUnit.MILLISECONDS.toHours(timeTaken),
                TimeUnit.MILLISECONDS.toMinutes(timeTaken) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(timeTaken)),
                TimeUnit.MILLISECONDS.toSeconds(timeTaken) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(timeTaken)));
        System.out.println("Time taken: "+time);

    }

    private void saveToJson(Results results, String pathOfFile) {
        Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().registerTypeAdapter(Double.class, new DoubleTypeAdapter()).create();
        String json = gson.toJson(results);
        saveFile(json, pathOfFile);

    }

    private void saveFile(String text, String pathOfFile) {
        Writer writer;
        try {
            writer = new OutputStreamWriter(new FileOutputStream(pathOfFile), "utf-8");
            writer.write(text);
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(JsonPostProcessor.class.getName()).log(Level.SEVERE, "Cannot save:", ex);
        }

    }

}
