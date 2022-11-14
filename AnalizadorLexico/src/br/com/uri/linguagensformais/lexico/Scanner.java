package br.com.uri.linguagensformais.lexico;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Scanner {

	private char[] content;
	private int state;
	private int pos;
	
	public Scanner(String filename) {
		
		try {
			
			String txconteudo;
			
			txconteudo = new String(Files.readAllBytes(Paths.get(filename)),StandardCharsets.UTF_8);
			content = txconteudo.toCharArray();
			pos = 0;
			
			JLabel exibetexto = new JLabel(content.toString(),JLabel.TOP);
			
			
			// Janela dados
			JFrame Ftexto = new JFrame();
			Ftexto.setSize(500,500);
			Ftexto.add(exibetexto);
			Ftexto.setVisible(true);
			
			
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
	}
	
	public Token nextToken() {
		char currentChar;
		if (isEOF()) {
			return null;
		}
		state = 0;
		while (true) {
			currentChar = nextChar();
			
			switch(state) {
			case 0:
				if (isChar(currentChar)) {
					state = 1;
				}else if (isDigit(currentChar)){
					state = 3;
				}
				break;
			case 1:
				if (isChar(currentChar)) {
					state = 1;
				}else if (isDigit(currentChar)){
					state = 3;
				}
			}
			
		}
	}
	
	private boolean isDigit(char c) {
		return c >='0' && c <= '9';
	}
	
	private boolean isChar(char c) {
		return (c >='a' && c <= 'z') || (c >='A' && c <= 'Z');
	}
	
	private boolean isOperator(char c) {
		return c =='>' || c == '<' || c == '=' || c == '!' ;
	}
	
	private boolean isSpace(char c) {
		return c ==' ' || c == '\t' || c == '\n' || c == '\r' ;
	}
	
	private char nextChar() {
		return content[pos++];
	}
	
	private boolean isEOF() {
		return pos == content.length;
	}
	
	private int back() {
		return pos--;
	}
	
}
