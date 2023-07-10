package com.fx.w2p.parts;

import com.fx.w2p.pojo.UvVector;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author pscha
 */
public class PartUvVectors {

    private final List<String> list;

    public PartUvVectors(List<String> list) {
        this.list = list;
    }

    public String getString() {
        String erg = "uv_vectors {\n";
        long size = list.stream().filter(str -> str.split(" ")[0].equalsIgnoreCase("vt")).count();
        erg += size + ",\n";

        if (size == 0) {
            return "";
        }

        List<String> vertexList = list.stream().filter(str -> str.split(" ")[0].equalsIgnoreCase("vt")).collect(Collectors.toList());
        List<UvVector> vectorList = new ArrayList<>();

        for (String line : vertexList) {
            String parts[] = line.split(" ");
            double x = Double.valueOf(parts[1]);
            double y = Double.valueOf(parts[2]);

            vectorList.add(new UvVector(x, y));
        }

        for (int i = 0; i < vectorList.size(); i++) {
            UvVector uvVector = vectorList.get(i);
            erg += uvVector.toString();
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
