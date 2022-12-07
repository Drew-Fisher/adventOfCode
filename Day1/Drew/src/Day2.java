import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class Day2 {

    public static String solution(String filePath){
        int day = 2;
        int partOne = partOne(filePath);
        int partTwo = partTwo(filePath);
        return "Day " + day +" solutions: " + "\n" + " Part 1: " + partOne + "\n" + " Part 2: " + partTwo;
    }

    private static int partOne(String path){

        //read input file
        List<List<String>> games = utils.readInput(path);

        //calculate scores
        Map<String, Integer> points = new HashMap<>();
        points.put("X",1);
        points.put("Y",2);
        points.put("Z",3);

        Map<String, String> winsAgainst = new HashMap<>();
        winsAgainst.put("A","Y");
        winsAgainst.put("B","Z");
        winsAgainst.put("C","X");

        Map<String, String> isEqual = new HashMap<>();
        isEqual.put("A","X");
        isEqual.put("B","Y");
        isEqual.put("C","Z");

        List<Integer> scores = games.stream().map(game -> {
            // get points for move you played
            Integer score = points.get(game.get(1));

            if(winsAgainst.get(game.get(0)).equals(game.get(1))){
                score += 6;
            }
            else if (isEqual.get(game.get(0)).equals(game.get(1))) {
                score += 3;
            }
            return score;
        }).collect(Collectors.toList());

        //sum scores
        return scores.stream().reduce(0, Integer::sum);
    }

    private static int partTwo(String path){

        //read input file
        List<List<String>> games = utils.readInput(path);

        //Point Maps
        Map<String, Integer> points = new HashMap<>();
        points.put("X",1);
        points.put("Y",2);
        points.put("Z",3);

        Map<String, String> winsAgainst = new HashMap<>();
        winsAgainst.put("A","Y");
        winsAgainst.put("B","Z");
        winsAgainst.put("C","X");

        Map<String, String> loseAgainst = new HashMap<>();
        loseAgainst.put("A","Z");
        loseAgainst.put("B","X");
        loseAgainst.put("C","Y");

        Map<String, String> isEqual = new HashMap<>();
        isEqual.put("A","X");
        isEqual.put("B","Y");
        isEqual.put("C","Z");

        List<Integer> scores = games.stream().map(game -> {

            if(game.get(1).equals("X")){
                return points.get(loseAgainst.get(game.get(0)));
            }
            else if (game.get(1).equals("Y")) {
                return points.get(isEqual.get(game.get(0))) + 3;
            }

            return points.get(winsAgainst.get(game.get(0))) + 6;

        }).collect(Collectors.toList());

        return scores.stream().reduce(0, Integer::sum);
    }

    private static class utils{
        public static List<List<String>> readInput(String path) {
            Scanner scanner = null;
            try {
                scanner = new Scanner(new File(path));
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            List<List<String>> result = new ArrayList<List<String>>();
            while (scanner.hasNextLine()){
                String line = scanner.nextLine();
                List<String> letters = Arrays.stream(line.split(" ")).toList();
                result.add(letters);
            }
            return result;
        }
    }
}