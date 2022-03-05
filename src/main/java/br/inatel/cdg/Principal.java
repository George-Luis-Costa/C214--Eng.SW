package br.inatel.cdg;

import br.inatel.cdg.model.Game;
import br.inatel.cdg.model.Platform;
import br.inatel.cdg.model.Publisher;
import br.inatel.cdg.service.ServiceGame;
import br.inatel.cdg.utils.CsvUtils;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Principal {

    public static void main(String[] args) {

        Path caminho = null;
        try {
            //Recebe o caminho a partir de resources
            caminho = Paths.get(ClassLoader.getSystemResource("vendas-games.csv").toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        //Cria um colecao apartir do mapeamento da classe CsvUtils
        List<Game> gameList = CsvUtils.readGameCsv(caminho);


        int numLines = gameList.size();
        System.out.println("Numero de linhas: " + numLines);

        //Lista recebe o gamelist e o enum com a plataforma respectiva

        List<Game> ps4Games = ServiceGame.getListByPlatform(gameList, Platform.X360);
        System.out.println("Numero de jogos de X360: " + ps4Games.size());
        //imprimindo o nome dos jogos de X360
        ps4Games.forEach(e -> System.out.println(e.getName()));

        System.out.println("");

        List<Game> activisionGames = ServiceGame.getByPuBlisher(gameList, Publisher.Nintendo);
        System.out.println("Numero de jogos da Nintendo: " + activisionGames.size());
        activisionGames.forEach(e -> System.out.println(e.getName()));
        //imprimindo o nome dos jogos da Nintendo

    }

}
