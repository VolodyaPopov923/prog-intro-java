import com.sun.source.tree.BreakTree;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Scanner implements AutoCloseable {
    private final StringBuilder Result = new StringBuilder();
    private int numSep = 0;
    private boolean flag = true;
    private int index = 0;
    private char[] buffer = new char[256];
    private final Reader reader;
    Scanner (String string){
        this.reader = new StringReader(string);
        WriteInBuffer();
    }
    Scanner (InputStream input){
        this.reader = new InputStreamReader(input, StandardCharsets.UTF_8);
        WriteInBuffer();
    }
    private void WriteInBuffer(){
        try{
            int s;
            if ((s = reader.read(buffer)) != -1){
                if (s != this.buffer.length){
                    buffer = Arrays.copyOf(buffer, s);
                }
            }else{
                buffer = new char[0];
            }
        } catch (IOException e) {
            System.err.println("Write IO error " + e.getMessage());
        }
    }
    private boolean checkbuffer(){
        if (index == buffer.length){
                WriteInBuffer();
                index = 0;
            return buffer.length == 0;
            }
        return false;
    }
    public boolean hasNextInt(){
        Result.setLength(0);
        while (!checkbuffer()) {
            for (int i = index; i < buffer.length; i++) {
                char charact = buffer[i];
                index++;
                if (Character.isDigit(charact) || (charact == '-')) {
                    Result.append(charact);
                } else {
                    if (!Result.isEmpty()) {
                        return true;
                    }
                }
            }
        }
        return !Result.isEmpty();
    }
    public int nextInt(){
        return  Integer.parseInt(Result.toString());
    }
    public boolean hasNextLine(){
        Result.setLength(0);
        while (!checkbuffer()) {
            for (int i = index; i < buffer.length; i++) {
                StringBuilder sb = new StringBuilder();
                char charact = buffer[i];
                index++;
                if (charact == System.lineSeparator().charAt(numSep)){
                    numSep++;
                    sb.append(charact);
                    if (numSep == System.lineSeparator().length()){
                        sb.setLength(0);
                        numSep = 0;
                        if (flag){
                            return true;
                        }
                        flag = true;
                        if (!Result.isEmpty()) {
                            return true;
                        }
                    }
                } else{
                    Result.append(sb);
                    flag = false;
                    Result.append(charact);
                }
            }
        }
    return !Result.isEmpty();
    }
    public String nextLine(){
        return Result.toString();
    }
    public boolean hasNext(){
        Result.setLength(0);
        while (!checkbuffer()) {
            for (int i = index; i < buffer.length; i++) {
                StringBuilder sb = new StringBuilder();
                char charact = buffer[i];
                index++;
                if (Character.isWhitespace(charact)) {
                    if (!Result.isEmpty()) {
                        return true;
                    }
                    continue;
                }
                if (charact == System.lineSeparator().charAt(numSep)){
                    numSep++;
                    sb.append(charact);
                    if (numSep == System.lineSeparator().length()){
                        sb.setLength(0);
                        numSep = 0;
                        if (flag){
                            return true;
                        }
                        flag = true;
                        if (!Result.isEmpty()) {
                            return true;
                        }
                    }
                } else{
                    Result.append(sb);
                    flag = false;
                    Result.append(charact);
                }
            }
        }
    return !Result.isEmpty();
    }
    public String next(){
        return Result.toString();
    }
    public boolean hasNextWord(){
        Result.setLength(0);
        while (!checkbuffer()) {
            for (int i = index; i < buffer.length; i++) {
                char charact = buffer[i];
                index++;
                if ((Character.getType(charact) == Character.DASH_PUNCTUATION) || (charact == '\'') || Character.isLetter(charact)) {
                    Result.append(charact);
                } else {
                    if (!Result.isEmpty()) {
                        return true;
                    }
                }
            }
        }
    return !Result.isEmpty();
    }
    public String nextWord(){
        return Result.toString();
    }
    public void close(){
        try{
            reader.close();
        } catch(IOException e){
            System.err.println("IO ERROR : " + e.getMessage());
        }
    }
}