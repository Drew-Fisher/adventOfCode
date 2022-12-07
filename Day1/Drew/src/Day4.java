import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class Day4 {
    public static String solution(String filePath){
        int day = 2;
        int partOne = partOne(filePath);
        int partTwo = partTwo(filePath);
        return "Day " + day +" solutions: " + "\n" + " Part 1: " + partOne + "\n" + " Part 2: " + partTwo;
    }

    private static int partOne(String path){
        List<List<List<Integer>>> section = utils.readInput(path);
        int count = 0;
        for(int x = 0; x < section.size(); x++){
            List<List<Integer>> pair = section.get(x);
            List<Integer> elf_1 = pair.get(0);
            List<Integer> elf_2 = pair.get(1);
            if(elf_1.get(0) <= elf_2.get(0) && elf_1.get(1) >= elf_2.get(1)){
                count += 1;
            } else if (elf_2.get(0) <= elf_1.get(0) && elf_2.get(1) >= elf_1.get(1)) {
                count += 1;
            }
        }
        return count;
    }

    private static int partTwo(String path){
        List<List<List<Integer>>> section = utils.readInput(path);
        int count = 0;
        for(int x = 0; x < section.size(); x++){
            List<List<Integer>> pair = section.get(x);
            List<Integer> elf_1 = pair.get(0);
            List<Integer> elf_2 = pair.get(1);
            if(elf_1.get(0) >= elf_2.get(0) && elf_1.get(0) <= elf_2.get(1)){
                count += 1;
            } else if (elf_2.get(0) >= elf_1.get(0) && elf_2.get(0) <= elf_1.get(1)) {
                count += 1;
            }
        }
        return count;
    }

    private static class utils{
        public static List<List<List<Integer>>> readInput(String path) {
            Scanner scanner = null;
            try {
                scanner = new Scanner(new File(path));
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            List<List<List<Integer>>> result = new ArrayList<>();
            while (scanner.hasNextLine()){
                String line = scanner.nextLine();
                List<String> pairStrings = Arrays.stream(line.split(",")).toList();
                List<List<Integer>> letters = pairStrings.stream().map(string -> {
                    return Arrays.stream(string.split("-")).map(Integer::parseInt).collect(Collectors.toList());
                }).collect(Collectors.toList());
                result.add(letters);
            }
            return result;
        }
    }
}
