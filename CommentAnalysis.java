

import java.io.*;

public class CommentAnalysis {

    int codeLines = 0, commentLines = 0, totalLines = 0;
    boolean commentStarted = false;
    static File inFile = null;

    public static void main(String[] args) {
        if (0 < args.length) {
            // text file will be passed during run time
            inFile = new File(args[0]);
        } else {
            //System.out.println("Cant Find The File Specified : " + inFile);
            System.out.println(0);
        }
        CommentAnalysis obj = new CommentAnalysis();
        obj.analyzeFile();
    }

    public void analyzeFile() {
        BufferedReader br = null;
        String sCurrentLine = null;
        boolean sameLine = false;

        try {
            // passing the text file location for FileReader.
            br = new BufferedReader(new FileReader(inFile));

            // Looping through the text file
            while ((sCurrentLine = br.readLine()) != null) {
                sCurrentLine = sCurrentLine.trim();
                sameLine = false;
                //System.out.println(sCurrentLine);
                while(sCurrentLine != null && sCurrentLine.length() > 0) {
                    //System.out.println("for line: " + sCurrentLine + " and sameLine:" + sameLine);
                    sCurrentLine = analyzeLine(sCurrentLine, sameLine);
                    sameLine = true;
                }
            }
            // excluding the number of lines that has comments and new lines
            totalLines = codeLines + commentLines;
            System.out.println("Total number of Lines are : " + totalLines);
            System.out.println("Number of comments: " + commentLines);
            System.out.println("Number of code lines: " + codeLines);
            double cPercent = (double)commentLines/totalLines;
            cPercent = cPercent * 100;
            System.out.println("Ratio is: " + cPercent);
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
        finally {
            try {
                // close bufferReader
                if (br != null) {
                    br.close();
                }
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public String analyzeLine(String sCurrentLine, boolean sameLine) {        
        
        if(commentStarted && sCurrentLine.contains("*/")) {
            if (!sameLine)
                commentLines++;
            commentStarted = false;
            if (!sCurrentLine.endsWith("*/"))
                sCurrentLine = sCurrentLine.substring(sCurrentLine.indexOf("*/")+2).trim();
            else
                sCurrentLine = null;
        }
        else if(sCurrentLine.startsWith("//")) {
            if (!sameLine)
                commentLines++;
            sCurrentLine = null;
        }
        else if(sCurrentLine.contains("/*")) {            
            commentStarted = true;
            if (!sCurrentLine.startsWith("/*")){
                if (!sameLine)
                    codeLines++;
                sCurrentLine = sCurrentLine.substring(sCurrentLine.indexOf("/*")).trim();                
            }
            else {
                if (!sameLine)
                    commentLines++;
                if (sCurrentLine.contains("*/")) {
                    commentStarted = false;
                    if (!sCurrentLine.endsWith("*/"))
                        sCurrentLine = sCurrentLine.substring(sCurrentLine.indexOf("*/")+2).trim();
                    else
                        sCurrentLine = null;
                }
                else
                    sCurrentLine = null;
            }
        }        
        else if(commentStarted) {
            if (!sameLine)
                commentLines++;
            sCurrentLine = null;
        }
        else {
            commentStarted = false;
            if (!sameLine)
                codeLines++;
            if (sCurrentLine.contains(";")) {
                if (!sCurrentLine.endsWith(";")) {
                    sCurrentLine = sCurrentLine.substring(sCurrentLine.indexOf(";")+1).trim();
                }
                else
                    sCurrentLine = null;
            }
            else
                sCurrentLine = null;
        }

        return sCurrentLine;
    }
}
