import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 清理gradle缓存的snapshot依赖包
 * @author jiaoziang
 * @date 2021/7/23
 **/
public class CleanGradleSnapshotCache {

    List<File> snapshotFiles = new ArrayList<>();
    StringBuilder resMsg = new StringBuilder();

    static FileFilter snapshotFilter = pathname -> pathname.getName().endsWith("SNAPSHOT");
    static String jarPath = new StringBuilder(System.getProperty("user.home"))
            .append(File.separatorChar)
            .append(".gradle")
            .append(File.separatorChar)
            .append("caches")
            .append(File.separatorChar)
            .append("modules-2")
            .append(File.separatorChar)
            .append("files-2.1")
            .toString();

    public String cleanGradleSnapshotCache() {
        System.out.println("invoke cleanGradleSnapshotCache");
        File cacheDir = new File(jarPath);
        if (!cacheDir.exists() || !cacheDir.isDirectory()) {
            return "no snapshot cache";
        }
        File[] groupDirs = cacheDir.listFiles();
        if (groupDirs == null) {
            return "no snapshot cache";
        }
        Arrays.stream(groupDirs).forEach(this::findSnapshotFileRecursively);
        return resMsg.length() > 0 ? resMsg.toString() : "no snapshot cache";
    }

    public void findSnapshotFileRecursively(File root) {
        if (root == null || !root.exists()) {
            return;
        }
        if (snapshotFilter.accept(root)) {
            snapshotFiles.add(root);
            return;
        }
        File[] children = null;
        if (!root.isDirectory() || (children = root.listFiles()) == null) {
            return;
        }
        Arrays.stream(children).forEach(this::findSnapshotFileRecursively);
        snapshotFiles.forEach(this::deleteRecursively);
    }

    public void deleteRecursively(File root) {
        if (root == null || !root.exists()) {
            return;
        }
        File[] children = null;
        if (root.isDirectory() && (children = root.listFiles()) != null) {
            Arrays.stream(children).forEach(this::deleteRecursively);
        }
        boolean delete = root.delete();
        String msg = delete ? "SUCCESS : " + root : "FAIL : " + root;
        resMsg.append(msg).append("\n");
    }

}
