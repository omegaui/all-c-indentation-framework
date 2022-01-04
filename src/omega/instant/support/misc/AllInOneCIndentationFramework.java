package omega.instant.support.misc;
import omega.instant.support.java.assist.CodeTokenizer;

import java.util.LinkedList;

import omega.ui.component.Editor;

import omega.instant.support.AbstractIndentationFramework;

import static omega.instant.support.java.framework.IndentationFramework.*;

public class AllInOneCIndentationFramework extends AbstractIndentationFramework{
	@Override
	public void indent(Editor textArea) {
		LinkedList<String> lines = CodeTokenizer.tokenizeWithoutLoss(textArea.getText(), '\n');
		int caretPos = textArea.getCaretPosition();
		int caretLineNumber = textArea.getCaretLineNumber();
		textArea.setText("");
		int tabs = 0;
		int lineN = 0;
		boolean needsExtraTab = false;
		boolean containsEqual = false;
		for(String token : lines){
			token = token.trim();
			lineN++;
			if(lineN <= caretLineNumber)
				caretPos += (tabs/textArea.getTabSize());
			
			if(count('{', token) == count('}', token))
				containsEqual = true;
			else
				containsEqual = false;
			
			if(!containsEqual)
				tabs -= count('}', token);
			
			if(!needsExtraTab)
				textArea.append(getTabs(tabs) + token + "\n");
			else{
				textArea.append(getTabs(tabs) + getTabs(1) + token + "\n");
				needsExtraTab = false;
			}
			if(!containsEqual)
				tabs += count('{', token);
			
			if((token.startsWith("if") && !token.contains(";")) || token.startsWith("while") || token.startsWith("for") || token.startsWith("else")){
				if(count('{', token) == 0 && count('(', token) == count(')', token))
					needsExtraTab = true;
			}
		}
		textArea.setCaretPosition(caretPos + 1);
	}
	
	@Override
	public boolean canIndent(Editor editor) {
		return editor.currentFile != null && 
			(editor.currentFile.getName().endsWith(".c") || editor.currentFile.getName().endsWith(".cpp") || editor.currentFile.getName().endsWith(".cs"));
	}
	
}
