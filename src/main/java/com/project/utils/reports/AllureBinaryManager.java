package com.project.utils.reports;

import com.project.utils.ConstantPaths;
import com.project.utils.OSUtils;
import com.project.utils.TerminalUtils;
import com.project.utils.logs.logsManager;
import org.jsoup.Jsoup;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class AllureBinaryManager {

private static class lazyHolder {
    static final String Version = resolveVersion();

    private static String resolveVersion() {
        /*
        String url;
        try {
            url = Jsoup.connect("https://github.com/allure-framework/allure2/releases/tag/latest")
                    .followRedirects(true).execute().url().toString();
            return  url.split("/tag/")[1];
        } catch (IOException e) {
            logsManager.error("Cannot resolve Allure version", e.toString());
            return "2.43.0";
        }*/
        return "2.43.0";
    }
}

//  download  ZIP  FIle   for allure
    private static Path downloadZIP(String Version)
    {
        try {
            String url= ConstantPaths.ALLURE_ZIP_BASE_URL+Version+"/allure-commandline-"+Version+".zip";
            Path zipFile= Paths.get(ConstantPaths.EXTRACTION_DIR.toString(),"allure-command"+Version+".zip");
            if(!Files.exists(zipFile))
            {
                Files.createDirectories(ConstantPaths.EXTRACTION_DIR);
                try {
                    BufferedInputStream in =new BufferedInputStream(new URI(url).toURL().openStream());
                    OutputStream out=Files.newOutputStream(zipFile);
                    in.transferTo(out);
                }
                catch (Exception e)
                {
                    logsManager.error("invalid url");
                }

            }
            return zipFile;

        } catch (Exception e)
        {
            return Paths.get("");
        }

    }

    // extract zip  file

    private static  void extractZip(Path zipPath)
    {
        try {
            ZipInputStream zipInputStream=new ZipInputStream(Files.newInputStream(zipPath));
            ZipEntry entry;

            while (( entry=zipInputStream.getNextEntry())!=null)
            {
                Path filePath=Paths.get(ConstantPaths.EXTRACTION_DIR.toString(), entry.getName());
                if(entry.isDirectory())
                {
                    Files.createDirectories(filePath);
                }
                else {
                    Files.createDirectories(filePath.getParent());
                    Files.copy(zipInputStream,filePath);
                }
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    //download and  extract

    public static void downloadAndExtract() throws Exception {
        try {
            String Version= lazyHolder.Version;
            Path extractDire=Paths.get(ConstantPaths.EXTRACTION_DIR.toString(),"allure-"+Version);

            if(Files.exists(extractDire))
            {
                logsManager.info("Allure  binaries already exist");
                return;
            }
            if(!OSUtils.getCurrentOS().equals(OSUtils.OS.WINDOWS))
            {
                TerminalUtils.executeTerminalCommand("chmod ","u+x",ConstantPaths.USER_DIR.toString());
            }
            Path zipPath=downloadZIP(Version);
            extractZip(zipPath);
            logsManager.info("allure  binaries  downloaded and  extracted");
            if(!OSUtils.getCurrentOS().equals(OSUtils.OS.WINDOWS))
            {
                TerminalUtils.executeTerminalCommand("chmod ","u+x",getExecutable().toString());
            }
            Files.deleteIfExists(Files.list(ConstantPaths.EXTRACTION_DIR).filter(p->p.toString().endsWith(".zip")).findFirst().orElseThrow());
        } catch (Exception e) {
            throw new Exception(e);
        }
    }
    public static Path getExecutable()
    {
        String version= lazyHolder.Version;
        Path binaryPath=Paths.get(ConstantPaths.EXTRACTION_DIR.toString(),"allure-"+version,"bin","allure");
        return OSUtils.getCurrentOS()== OSUtils.OS.WINDOWS?
                binaryPath.resolveSibling(binaryPath.getFileName()+".bat"):
                binaryPath;
    }



}

