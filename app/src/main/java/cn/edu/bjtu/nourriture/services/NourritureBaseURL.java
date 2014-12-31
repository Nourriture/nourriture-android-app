package cn.edu.bjtu.nourriture.services;

/**
 * Created by Pavel Procházka on 31/12/14.
 */
public interface NourritureBaseURL {
    public String PRODUCTION_PLATFORM_URL           = "http://9la.dk/platform";
    public String PRODUCTION_PLATFORM_ANDROID_URL   = "http://9la.dk/android_platform"; //FIXME: verify correctness of URL!

    public String LOCALHOST_PLATFORM_URL            = "http://localhost:2121";
    public String LOCALHOST_PLATFORM_ANDROID_URL    = "http://localhost:8081";
}
