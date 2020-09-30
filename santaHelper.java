import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class santaHelper {

	List names = new ArrayList<>();
	Random rand = new Random();
	Boolean done = false;
	int num = 0;
	String chosen;

	public Map<String, String> sortOutSecretSanta(Map<String, Boolean> santaMap, Map<String, Boolean> recievingMap,
			Map<String, String> good2GoMap, Map<String, ArrayList<String>> badMap, Map<String, String> secretSantaMap) {
		for (String name : santaMap.keySet()) {
			names.add(name);
		}
		for (String name : santaMap.keySet()) {

			if (santaMap.get(name) != true) {
				while (done == false) {
					num = rand.nextInt(names.size());
					chosen = (String) names.get(num);
					if (recievingMap.get(chosen) == false) {
						if (badMap.get(name) != null) {
							if (badMap.get(name).contains(chosen) == true) {
								// bad do nothing
								break;
							}
						}
						if (name == chosen && num != names.size() - 1 && santaMap.get(name) == false
								&& recievingMap.get(chosen) == false) {
							chosen = (String) names.get(num + 1);
							secretSantaMap.put(name, chosen);
							recievingMap.put(chosen, true);
							santaMap.put(name, true);
							done = true;
						}

						else if (name == chosen && num == names.size() - 1) {
							chosen = (String) names.get(num - 1);
							secretSantaMap.put(name, chosen);
							recievingMap.put(chosen, true);
							santaMap.put(name, true);
							done = true;
						} else {
							secretSantaMap.put(name, chosen);
							recievingMap.put(chosen, true);
							santaMap.put(name, true);
							done = true;
						}
					}
					if (recievingMap.get(name) == true && santaMap.get(name) == true) {
						names.remove(name);
					} else if (recievingMap.get(chosen) == true && santaMap.get(chosen) == true) {
						names.remove(chosen);
					}
				}

				done = false;
			}
		}

		return secretSantaMap;

	}

	public void printOutList(Map<String, String> secretSantaMap) throws IOException {
		StringBuilder sb = new StringBuilder();

		for (String name : secretSantaMap.keySet()) {
			sb.append("Santa: " + name + " has to give a gift to: " + secretSantaMap.get(name) + " for Secret Santa");
			sb.append("\n");
		}

		File file = new File("the file to create the Secret Santa list on");

		FileWriter writer = new FileWriter(file);
		writer.write(sb.toString());
		writer.flush();
		writer.close();
	}
}
