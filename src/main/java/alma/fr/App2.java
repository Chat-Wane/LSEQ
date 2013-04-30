package alma.fr;

import java.util.ArrayList;

public class App2 {
	public static void main(String[] args) {

		boolean pngExport = false;
		boolean histoPrompt = true;
		String prefix = "en";
		Integer nRev = 1000; // wikipedia returns 500 revs max
		ArrayList<String> pageList = new ArrayList<String>();

		if (args.length < 2) {
			System.err.println("Wrong number of arguments");
			System.out.println("wikiExtractor [pageName]+");
			System.out.println("-n=N extract from revision 1 to N");
			System.out.println("-o to set the png export on");
			System.out.println("-Dhttp.proxyHost=adress to specify the proxy");
			System.out.println("-Dhttp.proxyPort=P with P the proxy port");
			System.out
					.println("-pre=prefix set the prefix of wikipedia's page, default(en)");
			System.out.println("-nh no histogramme prompted ");
			System.exit(0);
		} else {
			for (int i = 0; i < args.length; i++) {
				if (args[i].contains("-n=")) {
					nRev = Integer.parseInt(args[i].substring(3).trim());
				} else if (args[i].contains("-o")) {
					pngExport = true;
				} else if (args[i].startsWith("-D")) {
					if (args[i].startsWith("-Dhttp.proxyHost=")) {
						System.setProperty("http.proxyHost",
								args[i].substring(17).trim());
					}
					if (args[i].startsWith("-Dhttp.proxyPort=")) {
						System.setProperty("http.proxyPort",
								args[i].substring(17).trim());
					}
					// ignore
				} else if (args[i].contains("-pre=")) {
					prefix = args[i].substring(5).trim();
				} else if (args[i].contains("-nh")) {
					histoPrompt = false;
				} else {
					pageList.add(args[i]);
				}
			}

		}

		RunWiki rw = new RunWiki();
		for (String pageName : pageList) {

			System.out.println("Calling " + pageName);

			rw.nRev = nRev;
			rw.pageName = pageName;
			rw.prefix = prefix;

			rw.run();

		}

	}
}
