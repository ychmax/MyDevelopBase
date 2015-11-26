package cn.edu.zstu.app.sample_storage.utils;

/**
 * Created by lenovo on 2015/11/26.
 */

import android.content.Context;
import android.os.Environment;
import android.os.StatFs;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
public class FileHelperUtil {
    private Context context;
    private String SDPATH; //SD卡路径
    private String FILESPATH; //文件路径
    public FileHelperUtil(Context context) {
        this.context = context;
        SDPATH = Environment.getExternalStorageDirectory().getPath() + "//";
        FILESPATH = this.context.getFilesDir().getPath() + "//";
    }

    /**
     * 判断SDCard是否存在？是否可以进行读写
     */
    public boolean SDCardState() {
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {//表示SDCard存在并且可以读写
            return true;
        } else {
            return false;
        }
    }

    /**
     * 获取SDCard文件路径
     */
    public String SDCardPath() {
        if (SDCardState()) {//如果SDCard存在并且可以读写
            SDPATH = Environment.getExternalStorageDirectory().getPath();
            return SDPATH;
        } else {
            return null;
        }
    }

    /**
     * 获取SDCard 总容量大小(MB)
     */
    public long SDCardTotal() {
        if (null != SDCardPath() && SDCardPath().equals("")) {
            StatFs statfs = new StatFs(SDCardPath());
            //获取SDCard的Block总数
            long totalBlocks = statfs.getBlockCount();
            //获取每个block的大小
            long blockSize = statfs.getBlockSize();
            //计算SDCard 总容量大小MB
            long SDtotalSize = totalBlocks * blockSize / 1024 / 1024;
            return SDtotalSize;
        } else {
            return 0;
        }
    }

    /**
     * 获取SDCard 可用容量大小(MB)
     */
    public long SDCardFree() {
        if (null != SDCardPath() && SDCardPath().equals("")) {
            StatFs statfs = new StatFs(SDCardPath());
            //获取SDCard的Block可用数
            long availaBlocks = statfs.getAvailableBlocks();
            //获取每个block的大小
            long blockSize = statfs.getBlockSize();
            //计算SDCard 可用容量大小MB
            long SDFreeSize = availaBlocks * blockSize / 1024 / 1024;
            return SDFreeSize;
        } else {
            return 0;
        }
    }

    /**
     * 在SD卡上创建目录
     * @param dirName 要创建的目录名
     * @return 创建得到的目录
     */
    public File createSDDir(String dirName) {
        File dir = new File(SDPATH + dirName);
        if(!dir.exists())
            dir.mkdir();
        return dir;
    }

    /**
     * 删除SD卡上的目录
     *
     * @param dirName
     */
    public boolean delSDDir(String dirName) {
        File dir = new File(SDPATH + dirName);
        return delDir(dir);
    }

    /**
     * 在SD卡上创建文件
     * @throws IOException
     */
    public File createSDFile(String fileName) throws IOException {
        File file = new File(SDPATH + fileName);
        file.createNewFile();
        return file;
    }


    /**
     * 判断文件是否已经存在
     * @param fileName 要检查的文件名
     * @return boolean, true表示存在，false表示不存在
     */
    public boolean isFileExist(String fileName) {
        File file = new File(SDPATH + fileName);
        return file.exists();
    }

    /**
     * 删除SD卡上的文件
     * @param fileName
     */
    public boolean delSDFile(String fileName) {
        File file = new File(SDPATH + fileName);
        if (file == null || !file.exists() || file.isDirectory())
            return false;
        file.delete();
        return true;
    }

    /**
     * 修改SD卡上的文件或目录名
     * @param oldfileName
     */
    public boolean renameSDFile(String oldfileName, String newFileName) {
        File oleFile = new File(SDPATH + oldfileName);
        File newFile = new File(SDPATH + newFileName);
        return oleFile.renameTo(newFile);
    }

    /**
     * 拷贝SD卡上的单个文件
     *
     * @param srcFileName
     * @throws IOException
     */
    public boolean copySDFileTo(String srcFileName, String destFileName)
            throws IOException {
        File srcFile = new File(SDPATH + srcFileName);
        File destFile = new File(SDPATH + destFileName);
        return copyFileTo(srcFile, destFile);
    }

    /**
     * 拷贝SD卡上指定目录的所有文件
     * @param srcDirName
     * @param destDirName
     * @return
     * @throws IOException
     */
    public boolean copySDFilesTo(String srcDirName, String destDirName)
            throws IOException {
        File srcDir = new File(SDPATH + srcDirName);
        File destDir = new File(SDPATH + destDirName);
        return copyFilesTo(srcDir, destDir);
    }

    /**
     * 移动SD卡上的单个文件
     * @param srcFileName
     * @param destFileName
     * @return
     * @throws IOException
     */
    public boolean moveSDFileTo(String srcFileName, String destFileName)
            throws IOException {
        File srcFile = new File(SDPATH + srcFileName);
        File destFile = new File(SDPATH + destFileName);
        return moveFileTo(srcFile, destFile);
    }

    /**
     * 移动SD卡上的指定目录的所有文件
     *
     * @param srcDirName
     * @param destDirName
     * @return
     * @throws IOException
     */
    public boolean moveSDFilesTo(String srcDirName, String destDirName)
            throws IOException {
        File srcDir = new File(SDPATH + srcDirName);
        File destDir = new File(SDPATH + destDirName);
        return moveFilesTo(srcDir, destDir);
    }

//    /**
//     * 将文件写入sd卡。如:writeSDFile("test.txt");
//     */
//    public Output writeSDFile(String fileName) throws IOException {
//
//        File file = new File(SDPATH + fileName);
//
//        FileOutputStream fos = new FileOutputStream(file);
//
//        return new Output(fos);
//
//    }
//
//
//    /**
//     * 在原有文件上继续写文件。如:appendSDFile("test.txt");
//     */
//
//    public Output appendSDFile(String fileName) throws IOException {
//
//        File file = new File(SDPATH + fileName);
//
//        FileOutputStream fos = new FileOutputStream(file, true);
//
//        return new Output(fos);
//
//    }
//
//
//    /**
//     * 从SD卡读取文件。如:readSDFile("test.txt");
//     */
//
//    public Input readSDFile(String fileName) throws IOException {
//
//        File file = new File(SDPATH + fileName);
//
//        FileInputStream fis = new FileInputStream(file);
//
//        return new Input(fis);
//
//    }

    /**
     * 建立私有文件
     * @param fileName
     * @return
     * @throws IOException
     */
    public File creatDataFile(String fileName) throws IOException {
        File file = new File(FILESPATH + fileName);
        file.createNewFile();
        return file;
    }

    /**
     * 建立私有目录
     * @param dirName
     * @return
     */

    public File creatDataDir(String dirName) {

        File dir = new File(FILESPATH + dirName);

        dir.mkdir();

        return dir;

    }


    /**
     * 删除私有文件
     *
     * @param fileName
     * @return
     */

    public boolean delDataFile(String fileName) {

        File file = new File(FILESPATH + fileName);

        return delFile(file);

    }


    /**
     * 删除私有目录
     *
     * @param dirName
     * @return
     */

    public boolean delDataDir(String dirName) {

        File file = new File(FILESPATH + dirName);

        return delDir(file);

    }


    /**
     * 更改私有文件名
     *
     * @param oldName
     * @param newName
     * @return
     */

    public boolean renameDataFile(String oldName, String newName) {

        File oldFile = new File(FILESPATH + oldName);

        File newFile = new File(FILESPATH + newName);

        return oldFile.renameTo(newFile);

    }


    /**
     * 在私有目录下进行文件复制
     *
     * @param srcFileName  ： 包含路径及文件名
     * @param destFileName
     * @return
     * @throws IOException
     */

    public boolean copyDataFileTo(String srcFileName, String destFileName)

            throws IOException {

        File srcFile = new File(FILESPATH + srcFileName);

        File destFile = new File(FILESPATH + destFileName);

        return copyFileTo(srcFile, destFile);

    }


    /**
     * 复制私有目录里指定目录的所有文件
     *
     * @param srcDirName
     * @param destDirName
     * @return
     * @throws IOException
     */

    public boolean copyDataFilesTo(String srcDirName, String destDirName)

            throws IOException {

        File srcDir = new File(FILESPATH + srcDirName);

        File destDir = new File(FILESPATH + destDirName);

        return copyFilesTo(srcDir, destDir);

    }


    /**
     * 移动私有目录下的单个文件
     *
     * @param srcFileName
     * @param destFileName
     * @return
     * @throws IOException
     */

    public boolean moveDataFileTo(String srcFileName, String destFileName)

            throws IOException {

        File srcFile = new File(FILESPATH + srcFileName);

        File destFile = new File(FILESPATH + destFileName);

        return moveFileTo(srcFile, destFile);

    }


    /**
     * 移动私有目录下的指定目录下的所有文件
     *
     * @param srcDirName
     * @param destDirName
     * @return
     * @throws IOException
     */

    public boolean moveDataFilesTo(String srcDirName, String destDirName)

            throws IOException {

        File srcDir = new File(FILESPATH + srcDirName);

        File destDir = new File(FILESPATH + destDirName);

        return moveFilesTo(srcDir, destDir);

    }


//    /**
//     * 将文件写入应用私有的files目录。如:writeFile("test.txt");
//     */
//
//    public Output wirteFile(String fileName) throws IOException {
//
//        OutputStream os = context.openFileOutput(fileName,
//
//                Context.MODE_WORLD_WRITEABLE);
//
//        return new Output(os);
//
//    }
//
//
//    /**
//     * 在原有文件上继续写文件。如:appendFile("test.txt");
//     */
//
//    public Output appendFile(String fileName) throws IOException {
//
//        OutputStream os = context.openFileOutput(fileName, Context.MODE_APPEND);
//
//        return new Output(os);
//
//    }
//
//
//    /**
//     * 从应用的私有目录files读取文件。如:readFile("test.txt");
//     */
//
//    public Input readFile(String fileName) throws IOException {
//
//        InputStream is = context.openFileInput(fileName);
//
//        return new Input(is);
//
//    }

    /**
     * 将一个输入流中的内容写入到SD卡上生成文件
     *
     * @param path        文件目录
     * @param fileName    文件名
     * @param inputStream 字节输入流
     * @return 得到的文件
     */

    public File writeToSDCard(String path, String fileName,

                              InputStream inputStream) {

        File file = null;

        OutputStream output = null;

        try {

            createSDDir(path);

            file = createSDFile(path + fileName);

            output = new FileOutputStream(file);

            byte buffer[] = new byte[4 * 1024];

            while ((inputStream.read(buffer)) != -1) {

                output.write(buffer);

            }

            output.flush();

        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            try {

                output.close();

            } catch (Exception e) {

                e.printStackTrace();

            }

        }

        return file;

    }

    /**
     * 删除一个文件
     *
     * @param file
     * @return
     */

    public boolean delFile(File file) {

        if (file.isDirectory())

            return false;

        return file.delete();

    }

    /**
     * 删除一个目录（可以是非空目录）
     *
     * @param dir
     */

    public boolean delDir(File dir) {

        if (dir == null || !dir.exists() || dir.isFile()) {

            return false;

        }

        for (File file : dir.listFiles()) {

            if (file.isFile()) {

                file.delete();

            } else if (file.isDirectory()) {

                delDir(file);// 递归

            }

        }

        dir.delete();

        return true;

    }

    /**
     * 拷贝一个文件,srcFile源文件，destFile目标文件
     *
     * @param srcFile
     * @throws IOException
     */

    public boolean copyFileTo(File srcFile, File destFile) throws IOException {

        if (srcFile.isDirectory() || destFile.isDirectory())

            return false;// 判断是否是文件

        FileInputStream fis = new FileInputStream(srcFile);

        FileOutputStream fos = new FileOutputStream(destFile);

        int readLen = 0;

        byte[] buf = new byte[1024];

        while ((readLen = fis.read(buf)) != -1) {

            fos.write(buf, 0, readLen);

        }

        fos.flush();

        fos.close();

        fis.close();

        return true;

    }

    /**
     * 拷贝目录下的所有文件到指定目录
     *
     * @param srcDir
     * @param destDir
     * @return
     * @throws IOException
     */

    public boolean copyFilesTo(File srcDir, File destDir) throws IOException {

        if (!srcDir.isDirectory() || !destDir.isDirectory())

            return false;// 判断是否是目录

        if (!destDir.exists())

            return false;// 判断目标目录是否存在

        File[] srcFiles = srcDir.listFiles();

        for (int i = 0; i < srcFiles.length; i++) {

            if (srcFiles[i].isFile()) {

                // 获得目标文件

                File destFile = new File(destDir.getPath() + "//"

                        + srcFiles[i].getName());

                copyFileTo(srcFiles[i], destFile);

            } else if (srcFiles[i].isDirectory()) {

                File theDestDir = new File(destDir.getPath() + "//"

                        + srcFiles[i].getName());

                copyFilesTo(srcFiles[i], theDestDir);

            }

        }

        return true;

    }

    /**
     * 移动一个文件
     *
     * @param srcFile
     * @param destFile
     * @return
     * @throws IOException
     */

    public boolean moveFileTo(File srcFile, File destFile) throws IOException {

        boolean iscopy = copyFileTo(srcFile, destFile);

        if (!iscopy)

            return false;

        delFile(srcFile);

        return true;

    }

    /**
     * 移动目录下的所有文件到指定目录
     *
     * @param srcDir
     * @param destDir
     * @return
     * @throws IOException
     */

    public boolean moveFilesTo(File srcDir, File destDir) throws IOException {

        if (!srcDir.isDirectory() || !destDir.isDirectory()) {

            return false;

        }

        File[] srcDirFiles = srcDir.listFiles();

        for (int i = 0; i < srcDirFiles.length; i++) {

            if (srcDirFiles[i].isFile()) {

                File oneDestFile = new File(destDir.getPath() + "//"

                        + srcDirFiles[i].getName());

                moveFileTo(srcDirFiles[i], oneDestFile);

                delFile(srcDirFiles[i]);

            } else if (srcDirFiles[i].isDirectory()) {

                File oneDestFile = new File(destDir.getPath() + "//"

                        + srcDirFiles[i].getName());

                moveFilesTo(srcDirFiles[i], oneDestFile);

                delDir(srcDirFiles[i]);

            }


        }

        return true;

    }

}
