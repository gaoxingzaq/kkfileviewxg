package cn.keking.config;

import org.springframework.boot.Banner;
import org.springframework.core.env.Environment;

import java.io.PrintStream;

/**
 * @author kl (http://kailing.pub)
 * @since 2021/2/8
 */
public class AppBanner implements Banner {
    @Override
    public void printBanner(Environment environment, Class<?> sourceClass, PrintStream out) {
        out.println(
                "  _      _      ______   _   _         __      __  _                   \n" +
                " | |    | |    |  ____| (_) | |        \\ \\    / / (_)                  \n" +
                " | | __ | | __ | |__     _  | |   ___   \\ \\  / /   _    ___  __      __\n" +
                " | |/ / | |/ / |  __|   | | | |  / _ \\   \\ \\/ /   | |  / _ \\ \\ \\ /\\ / /\n" +
                " |   <  |   <  | |      | | | | |  __/    \\  /    | | |  __/  \\ V  V / \n" +
                " |_|\\_\\ |_|\\_\\ |_|      |_| |_|  \\___|     \\/     |_|  \\___|   \\_/\\_/  \n" +
                "                                                                     \n" +
                " => Spring Boot   ::  (v2.4.2)             QQ3 :: 433403637\n" +
                " => kkFileView    ::  (v4.6.3)             QQ2 :: 484680571\n" +
                " => github        ::  https://github.com/kekingcn/kkFileView\n" +
                " => gitee官方     ::  https://gitee.com/kekingcn/file-online-preview\n" +
				" => 高雄修改      ::  https://gitee.com/gaoxingzaq/file-online-preview-master\n");
    }
}
