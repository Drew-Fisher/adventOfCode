import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class Day3 {

    public static String solution(String filePath){
        int day = 3;
        int partOne = partOne(filePath);
        int partTwo = partTwo(filePath);

        return "Day " + day +" solutions: " + "\n" + " Part 1: " + partOne + "\n" + " Part 2: " + partTwo;
    }
    private static int partOne(String filePath){
        List<String> rucksacks = Utils.readInput(filePath);

        List<Integer> ruck_values = rucksacks.stream().map(sack -> {
            int midPoint = sack.length() / 2;
            String compartment_1 = sack.substring(0, midPoint);
            String compartment_2 = sack.substring(midPoint, sack.length());

            List<Character> characters = new ArrayList<Character>();

            for(int x = 0; x <= compartment_1.toCharArray().length-1; x++){
                if(compartment_2.contains(Character.toString(compartment_1.charAt(x))) && !characters.contains(compartment_1.charAt(x))){
                    characters.add(compartment_1.charAt(x));
                }
            }

            List<Integer> values = characters.stream().map(character -> {
                int value = 0;
                if(Character.isUpperCase(character)){
                    value = (int) character - 38;
                    return value;
                }
                value = (int) character - 96;
                return value;
            }).collect(Collectors.toList());

            return values.stream()
                    .reduce(0, Integer::sum);
        }).collect(Collectors.toList());
        return ruck_values.stream().reduce(0,Integer::sum);
    }

    private static int partTwo(String filePath){

        //read input
        List<String> rucksacks = Utils.readInput(filePath);

        //group elf's
        List<List<String>>rucksack_groups = new ArrayList<>();

        for(int x = 0; x < rucksacks.size(); x++){
            List<String> sub = rucksacks.subList(x,x+3);
            rucksack_groups.addAll(Collections.singleton(sub));
            x+=2;
        }
        
        //check for shared badges
        List<Character> badges = rucksack_groups.stream().map(group -> {
            Character badge = null;
            List<Character> memory = new ArrayList<>();
            for (int x = 0; x < group.get(0).toCharArray().length; x++){
                Character character = group.get(0).charAt(x);
                if(!memory.contains(group.get(0).charAt(0)) && group.get(1).contains(character.toString()) && group.get(2).contains(character.toString())){
                    memory.add(character);
                    badge = character;
                    break;
                }
            }
            return badge;
        }).collect(Collectors.toList());

        //sum priority
        List<Integer> values = badges.stream().map(character -> {
            int value = 0;
            if(Character.isUpperCase(character)){
                value = (int) character - 38;
                return value;
            }
            value = (int) character - 96;
            return value;
        }).collect(Collectors.toList());

        return values.stream()
                .reduce(0, Integer::sum);
    }

    private static class Utils{
        public static List<String> readInput(String path) {
            Scanner scanner = null;
            try {
                scanner = new Scanner(new File(path));
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            List<String> result = new ArrayList<String>();
            while (scanner.hasNextLine()){
                result.add(scanner.nextLine());
            }
            return result;
        }
    }
}
