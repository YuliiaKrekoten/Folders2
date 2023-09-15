import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        File games = new File("C:/Users/Admin/IdeaProjects/Folders2/Games");
        games.mkdirs();

        String[] folders = {"srs", "res","temp","saveGames",  "srs/test", "res/drawables", "res/vectors", "res/icons"};
        StringBuilder log = new StringBuilder();
        log.append(createFolders(games, folders));

        File main = new File(games, "srs/main" );
        main.mkdirs();
        String[] files = {"Main.java", "Utils.java"};
        log.append(createFiles(main, files));

        File temp = new File(games, "temp/temp.txt");
        log.append(createFile(temp));

        writeLog(log.toString(), "log.txt");
    }

    public static String createFolders(File parent, String[] folders) {
        StringBuilder log = new StringBuilder();
        for (String folder : folders) {
            File dir = new File(parent, folder);
            if (dir.mkdirs()) {
                log.append("Папка ").append(dir.getName()).append(" успешно создана!\n");
            } else {
                log.append("Ошибка при создании папки ").append(dir.getName()).append("!\n");
            }
        }
        return log.toString();
    }

    public static String createFiles(File parent, String[] files) {
        StringBuilder log = new StringBuilder();
        for (String file : files) {
            File newFile = new File(parent, file);
            try {
                if (newFile.createNewFile()) {
                    log.append("Файл ").append(newFile.getName()).append(" успешно создан!\n");
                } else {
                    log.append("Ошибка при создании файла ").append(newFile.getName()).append("!\n");
                }
            } catch (IOException e) {
                log.append("Ошибка при создании файла ").append(newFile.getName()).append("!\n");
                e.printStackTrace();
            }
        }
        return log.toString();
    }

    public static String createFile(File file) {
        StringBuilder log = new StringBuilder();
        try {
            if (file.createNewFile()) {
                log.append("Файл успешно создан: ").append(file.getAbsolutePath()).append("\n");
            } else {
                log.append("Файл уже существует: ").append(file.getAbsolutePath()).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return log.toString();
    }

    public static void writeLog(String log, String filePath) {
        try {
            FileWriter writer = new FileWriter(filePath);
            writer.write(log);
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}