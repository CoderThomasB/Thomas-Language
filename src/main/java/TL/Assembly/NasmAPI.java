package TL.Assembly;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

public abstract class NasmAPI {
	public static void ParesAssembly() throws IOException, InterruptedException {
		Process NasmProcess = Runtime.getRuntime().exec("nasm");
		NasmProcess.waitFor();
		
		System.out.println(
				new BufferedReader(
						new InputStreamReader(NasmProcess.getInputStream(), StandardCharsets.UTF_8))
						.lines()
						.collect(Collectors.joining("\n"))
		);
		System.err.println(
				new BufferedReader(
						new InputStreamReader(NasmProcess.getErrorStream(), StandardCharsets.UTF_8))
						.lines()
						.collect(Collectors.joining("\n"))
		);
	}
}
