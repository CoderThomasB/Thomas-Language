package TL.Assembly.x86;

import TL.Assembly.AssemblyBlock;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.stream.Collectors;

public abstract class NasmAPI {
	public static File ParesAssembly(AssemblyBlock TheAssemblyBlock) throws IOException, InterruptedException {
		File NasmInput = File.createTempFile( "nasmInput", ".txt" );
		File NasmOutput = File.createTempFile( "NasmOutput", "" );
		
		NasmInput.setWritable( true );
		Files.writeString( NasmInput.toPath(), TheAssemblyBlock.toString(), StandardCharsets.US_ASCII );
		
		System.out.println( NasmInput.getAbsolutePath() );
		System.out.println( NasmOutput.getAbsolutePath() );
		
		Process NasmProcess = Runtime.getRuntime( ).exec( "nasm " + NasmInput.getAbsolutePath( ) + " -o" + NasmOutput.getAbsolutePath());
		NasmProcess.waitFor( );
		
		System.out.println(
				new BufferedReader(
						new InputStreamReader( NasmProcess.getInputStream( ), StandardCharsets.UTF_8 ) )
						.lines( )
						.collect( Collectors.joining( "\n" ) )
		);
		System.err.println(
				new BufferedReader(
						new InputStreamReader( NasmProcess.getErrorStream( ), StandardCharsets.UTF_8 ) )
						.lines( )
						.collect( Collectors.joining( "\n" ) )
		);
		
		return NasmOutput;
	}
}
