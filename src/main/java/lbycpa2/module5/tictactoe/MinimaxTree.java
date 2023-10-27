package lbycpa2.module5.tictactoe;

import java.util.ArrayList;
import java.util.Objects;

public class MinimaxTree {

    public ArrayList<State> successorsOf(State state){
        ArrayList<State> possibleMoves = new ArrayList<>();
        int xMoves = 0;
        int oMoves = 0;
        String player;

        for (String s:
             state.getState()) {
            if(s.equals("X")){
                xMoves++;
            }
            else if (s.equals("O")){
                oMoves++;
            }
        }

        if (xMoves <= oMoves){
            player = "X";
        } else {
            player = "O";
        }

        for (int i = 0; i < 9; i++) {
            String[] newState = state.getState().clone();

            if (!Objects.equals(newState[i], "X") && !Objects.equals(newState[i], "O")){
                newState[i] = player;
                possibleMoves.add(new State(i, newState));
            }
        }
        return possibleMoves;
    }
    public String wins(State state, int a){
        return switch (a){
            case 0 -> state.getStateIndex(0) + state.getStateIndex(1) + state.getStateIndex(2);
            case 1 -> state.getStateIndex(3) + state.getStateIndex(4) + state.getStateIndex(5);
            case 2 -> state.getStateIndex(6) + state.getStateIndex(7) + state.getStateIndex(8);
            case 3 -> state.getStateIndex(0) + state.getStateIndex(3) + state.getStateIndex(6);
            case 4 -> state.getStateIndex(1) + state.getStateIndex(4) + state.getStateIndex(7);
            case 5 -> state.getStateIndex(2) + state.getStateIndex(5) + state.getStateIndex(8);
            case 6 -> state.getStateIndex(0) + state.getStateIndex(4) + state.getStateIndex(8);
            case 7 -> state.getStateIndex(2) + state.getStateIndex(4) + state.getStateIndex(6);
            default -> "";
        };
    }

    public int utilityOf(State state){
        for (int a = 0; a < 8; a++) {
             String line = wins(state,a);

             if(line.equals("XXX")){
                 return 1;
             } else if (line.equals("OOO")){
                 return -1;
             }
        }
        return 0;
    }

    public boolean isOver(State state){
        int taken = 0;
        for (int i = 0; i < 9; i++) {
            if(state.getStateIndex(i).equals("X") || state.getStateIndex(i).equals("O")){
                taken++;
            }
            String line = wins(state,i);

            if(line.equals("XXX") || line.equals("OOO")){
                return true;
            }

            if (taken == 9){
                return true;
            }
        }
        return false;
    }

    public int minValue(State state){
        if(isOver(state)){
            return utilityOf(state);
        }

        int y = Integer.MAX_VALUE;

        for (State possibleMove:successorsOf(state)) {
            y = Math.min(y,maxValue(possibleMove));
        }
//        System.out.println(y);
        return y;
    }

    public int maxValue(State state){
        if(isOver(state)){
            return utilityOf(state);
        }

        int y = Integer.MIN_VALUE;

        for (State possibleMove:successorsOf(state)) {
            y = Math.max(y,minValue(possibleMove));
        }
//        System.out.println(y);
        return y;
    }

    public int minMaxDecision (State state){
        ArrayList<State> possibleMoves = successorsOf(state);
        ArrayList<Integer> movesList = new ArrayList<>();

        for(State states: possibleMoves){
            movesList.add(minValue(states));
        }

        int max = movesList.get(0);
        int best = 0;

        for (int i = 1; i < movesList.size(); i++) {
            if(movesList.get(i) > max){
                max = movesList.get(i);
                best = i;
            }
        }

        System.out.println(possibleMoves);
        System.out.println(movesList);

        int move = possibleMoves.get(best).getPosition();
        System.out.println("Action: " + move);
        return move;
    }
}

