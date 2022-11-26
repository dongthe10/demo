import com.alibaba.dubbo.rpc.cluster.Cluster;
import org.apache.dubbo.common.extension.ExtensionLoader;

/**
 * @author hollly
 * @date 2022/6/10 23:54
 */
public class ExtensionTest {

    public static void main(String[] args) {
        Cluster adaptiveExtension = ExtensionLoader.getExtensionLoader(Cluster.class).getAdaptiveExtension();
        System.out.println(adaptiveExtension);
    }
}
