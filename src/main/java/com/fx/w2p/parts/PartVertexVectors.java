package com.fx.w2p.parts;

import com.fx.w2p.pojo.DoubleVector;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author pscha
 */
public class PartVertexVectors {

    private final List<String> list;

    public PartVertexVectors(List<String> list) {
        this.list = list;
    }

    public String getString() {
        String erg = "vertex_vectors {\n";
        long size = list.stream().filter(str -> str.split(" ")[0].equalsIgnoreCase("v")).count();
        erg += size + ",\n";

        List<String> vertexList = list.stream().filter(str -> str.split(" ")[0].equalsIgnoreCase("v")).collect(Collectors.toList());
        List<DoubleVector> vectorList = new ArrayList<>();

        for (String line : vertexList) {
            String parts[] = line.split(" ");
            double x = Double.valueOf(parts[1]);
            double y = Double.valueOf(parts[2]);
            double z = Double.valueOf(parts[3]);

            vectorList.add(new DoubleVector(x, y, z));
        }

        for (int i = 0; i < vectorList.size(); i++) {
            DoubleVector doubleVector = vectorList.get(i);
            erg += doubleVector.toString();
            if (i < vectorList.size() - 1) {
                erg += ",";
            }
            if ((i + 1) % 3 == 0) {
                erg += "\n";
            }

        }

        erg += "}\n";
        return erg;
    }
}
