import com.alibaba.dubbo.common.extension.ExtensionLoader;
import com.alibaba.dubbo.rpc.cluster.Cluster;

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
