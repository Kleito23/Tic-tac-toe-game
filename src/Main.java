import java.util.*;

public class Main {

    public static void main(String[] args) {
        char[][] gameBoard = {
                {' ','|',' ','|',' '},
                {'-','+','-','+','-'},
                {' ','|',' ','|',' '},
                {'-','+','-','+','-'},
                {' ','|',' ','|',' '}};
        printGameBoard(gameBoard);

        ArrayList<Integer> posicionesJugador = new ArrayList<>();
        ArrayList<Integer> posicionesCPU = new ArrayList<>();
        while(true) {
            Scanner scan = new Scanner(System.in);
            System.out.print("Escoje una posicion del 1 al 9: ");
            int playerPos = scan.nextInt();
            while(posicionesJugador.contains(playerPos) || posicionesCPU.contains(playerPos)){
                System.out.print("Posicion ya escogida, por favor escoja otra posicion: ");
                playerPos = scan.nextInt();
            }

            ponerPieza(gameBoard, playerPos,"player",posicionesJugador,posicionesCPU);
            String resultado = verificarGanador(posicionesJugador,posicionesCPU);
            if(resultado.length() > 0){
                printGameBoard(gameBoard);
                System.out.println(resultado);
                break;
            }
            Random ran = new Random();
            int cpuPos = ran.nextInt(9) + 1;
            while(posicionesJugador.contains(cpuPos) || posicionesCPU.contains(cpuPos)){
                cpuPos = ran.nextInt(9) + 1;
            }
            ponerPieza(gameBoard, cpuPos,"cpu",posicionesJugador,posicionesCPU);
            resultado = verificarGanador(posicionesJugador,posicionesCPU);
            if (resultado.length()>0){
                printGameBoard(gameBoard);
                System.out.println(resultado);
                break;
            }
            printGameBoard(gameBoard);

        }
    }

    public static void printGameBoard(char[][] gameBoard) {
        for (char[] fila : gameBoard) {
            for (char element : fila) {
                System.out.print(element);
            }
            System.out.println(" ");
        }
    }

    public static void ponerPieza(char[][] gameBoard, int pos, String player, List<Integer> posicionesJugador, List<Integer> posicionesCPU) {
        char piece = ' ';
        if (player.equals("player")){
             piece = 'X';
             posicionesJugador.add(pos);
        }else if (player.equals("cpu")){
             piece = 'O';
             posicionesCPU.add(pos);

        }
        switch (pos) {
            case 1 -> gameBoard[0][0] = piece;
            case 2 -> gameBoard[0][2] = piece;
            case 3 -> gameBoard[0][4] = piece;
            case 4 -> gameBoard[2][0] = piece;
            case 5 -> gameBoard[2][2] = piece;
            case 6 -> gameBoard[2][4] = piece;
            case 7 -> gameBoard[4][0] = piece;
            case 8 -> gameBoard[4][2] = piece;
            case 9 -> gameBoard[4][4] = piece;
            default -> {
            }
        }

    }



    public static String verificarGanador(List<Integer> posicionesJugador, List<Integer> posicionesCPU){
        List<Integer> topRow = Arrays.asList(1,2,3);
        List<Integer> midRow = Arrays.asList(4,5,6);
        List<Integer> botRow = Arrays.asList(7,8,9);
        List<Integer> leftCol = Arrays.asList(1,4,7);
        List<Integer> midCol = Arrays.asList(2,5,8);
        List<Integer> botCol = Arrays.asList(3,6,9);
        List<Integer> cross1 = Arrays.asList(1,5,9);
        List<Integer> cross2 = Arrays.asList(3,5,7);

        List<List> listWinnings = new ArrayList<>();
        listWinnings.add(topRow);
        listWinnings.add(midRow);
        listWinnings.add(botRow);
        listWinnings.add(leftCol);
        listWinnings.add(midCol);
        listWinnings.add(botCol);
        listWinnings.add(cross1);
        listWinnings.add(cross2);
        for(List l : listWinnings){
            if (posicionesJugador.containsAll(l)){
                return "Felicidades, haz ganado!";
            }else if(posicionesCPU.containsAll(l)){
                return "CPU Gana, lo siento :(";
            }else if(posicionesJugador.size() + posicionesCPU.size() == 9){
                return "Es un empate!";
            }

        }
        return "";
    }



}
