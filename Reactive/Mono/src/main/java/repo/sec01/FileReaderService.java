package repo.sec01;

import reactor.core.publisher.Mono;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileReaderService {
    //static String resource = Paths.get("").toAbsolutePath().toString();
    private static  Path PATH = Paths.get("");


    public static Mono<String> read(String file){
        return Mono.fromSupplier(()->readFile(file));
    }

    public static Mono<Void> write(String file , String contant){
            return Mono.fromRunnable(()->writeFile(file, contant));
    }

    public static Mono<Void> delete(String file){
        return Mono.fromRunnable(()-> deleteFile(file));
    }

    public static String readFile(String fileName){
        System.out.println(PATH.toAbsolutePath());
        try{
            return  Files.readString(PATH.resolve(fileName));
        } catch (IOException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    public static void writeFile(String fileName , String content){
        try {
            Files.writeString(PATH, content);
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    public static void deleteFile(String fileName){
        try{
            Files.delete(PATH.resolve(fileName));
        } catch (IOException e){
            throw  new RuntimeException(e);
        }
    }
}
