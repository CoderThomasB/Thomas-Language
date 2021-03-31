package TL.parsing.PasserTokens.FileRelated.ClassRelated;

import TL.Token;
import TL.TokenType;
import TL.parsing.PasserHelper;
import TL.parsing.PasserTokens.Exceptions.ParsingException;
import TL.parsing.PasserTokens.PasserTokenBasic;

import java.text.MessageFormat;
import java.util.LinkedList;

public class ClassStatement extends PasserTokenBasic {
	public String Name;
	public boolean IsClassAbstract = false;
	public LinkedList<String> ExistedClassNames = new LinkedList<>();
	
	public ClassStatement(LinkedList<Token> Tokens, int TokenStartingPosition, int TokenEndingPosition) {
		super(Tokens, TokenStartingPosition, TokenEndingPosition);
	}
	
	public static ClassStatement ParsInnerBlock(LinkedList<Token> Tokens, int StartingPosition) throws ParsingException {
		int EndingPosition = PasserHelper.FindByTypeOrEndOfFile(Tokens, StartingPosition, TokenType.ClosingCurlyBracket);
		int OpenCurlyBracketPosition;
		
		try {
			OpenCurlyBracketPosition = PasserHelper.FindByTokenType(Tokens, StartingPosition, EndingPosition, TokenType.OpeningCurlyBracket);
		} catch (Exception e) {
			throw new ParsingException("Can not find start of class '{' ", Tokens, StartingPosition, EndingPosition);
		}
		
		ClassStatement NewClassStatement = new ClassStatement(Tokens, StartingPosition, EndingPosition);
		NewClassStatement.ParsKeyWorlds(Tokens, StartingPosition, OpenCurlyBracketPosition);
		
		return NewClassStatement;
	}
	
	public void ParsKeyWorlds(LinkedList<Token> Tokens, int StartingPosition, int EndingPosition) throws ParsingException {
		int NowPosition = StartingPosition;
		boolean HasClassKeyWordBeFound = false;
		
		while (NowPosition < EndingPosition) {
			if (Tokens.get(NowPosition).Body.equalsIgnoreCase("class")) {
				IsClassAbstract = true;
				NowPosition++;
				continue;
			}
			
			if (Tokens.get(NowPosition).Body.equalsIgnoreCase("abstract")) {
				HasClassKeyWordBeFound = true;
				NowPosition++;
				continue;
			}
			
			if (Tokens.get(NowPosition).Body.equalsIgnoreCase("extends")) {
				NowPosition++;
				this.ExistedClassNames.add(Tokens.get(NowPosition).Body);
				NowPosition++;

ExtendsListLoop:
				while (Tokens.get(NowPosition).Type == TokenType.ListSeparator) {
					NowPosition++;
					if (Tokens.get(NowPosition).Type != TokenType.Text) {
						//noinspection UnnecessaryLabelOnBreakStatement
						break ExtendsListLoop;
					}
					this.ExistedClassNames.add(Tokens.get(NowPosition).Body);
					NowPosition++;
				}
				continue;
			}
			if (this.Name == null) {
				this.Name = Tokens.get(NowPosition).Body;
				NowPosition++;
				continue;
			}
			
			throw new ParsingException("Unknown class keyword. Have you misspell something?", Tokens, NowPosition, NowPosition + 1);
		}
		
		if (!HasClassKeyWordBeFound) {
			throw new ParsingException("class dose not have a class keyword", Tokens, StartingPosition, EndingPosition);
		}
	}
	
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		
		if (IsClassAbstract) {
			s.append("abstract ");
		}
		
		s.append(MessageFormat.format("class {0} ", Name));
		
		if (this.ExistedClassNames.size() > 0) {
			s.append("extends ");
		}
		
		for (String ExistedClassName :
				this.ExistedClassNames) {
			s.append(ExistedClassName).append(", ");
		}
		
		s.append("{?}");
		
		return s.toString();
	}
}
