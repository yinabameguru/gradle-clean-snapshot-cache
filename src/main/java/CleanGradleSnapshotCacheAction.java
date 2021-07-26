import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import org.jetbrains.annotations.NotNull;

/**
 * @author jiaoziang
 * @date 2021/7/23
 **/
public class CleanGradleSnapshotCacheAction extends AnAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        String msg = new CleanGradleSnapshotCache().cleanGradleSnapshotCache();
        Messages.showMessageDialog(e.getProject(), msg, e.getPresentation().getDescription(), Messages.getInformationIcon());
    }
}
