import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
*
*@author: soccereagle935
*
*/
public class santaDriver extends santaHelper {

	static int count = 1;
	static santaHelper helper = new santaHelper();

	public static void main(String[] args) {

		Map<String, Boolean> santaMap = new HashMap<String, Boolean>();
		Map<String, Boolean> recievingMap = new HashMap<String, Boolean>();
		Map<String, String> good2GoMap = new HashMap<String, String>();
		Map<String, ArrayList<String>> badMap = new HashMap<String, ArrayList<String>>();
		Map<String, String> secretSantaMap = new HashMap<String, String>();

		if (count == 1) {

			try {
				BufferedReader br = new BufferedReader(new FileReader("the file to create the Secret Santa list from"));
				StringBuilder sb = new StringBuilder();
				String line = br.readLine();
				String[] names;
				String[] nameHold;
				names = line.split(",");

				for (String name : names) {
					santaMap.put(name, false);
					recievingMap.put(name, false);
				}

				line = br.readLine();
				if (line != null) {
					names = line.split(",");

					for (String name : names) {
						nameHold = name.split("==");

						secretSantaMap.put(nameHold[0], nameHold[1]);
						santaMap.put(nameHold[0], true);
						recievingMap.put(nameHold[1], true);
					}
				}

				line = br.readLine();
				if (line != null) {
					names = line.split(",");

					for (String name : names) {
						nameHold = name.split("!=");
						ArrayList<String> temp = new ArrayList<String>();
						temp.add(nameHold[1]);

						if (badMap.containsKey(nameHold[0]) == true) {
							temp.addAll(badMap.get(nameHold[0]));
						}
						badMap.put(nameHold[0], temp);
					}
				}
				helper.sortOutSecretSanta(santaMap, recievingMap, good2GoMap, badMap, secretSantaMap);
				helper.printOutList(secretSantaMap);
				count--;
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

}
