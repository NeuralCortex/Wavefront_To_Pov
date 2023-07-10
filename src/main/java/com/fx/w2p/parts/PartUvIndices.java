package com.fx.w2p.parts;

import com.fx.w2p.pojo.IntVector;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author pscha
 */
public class PartUvIndices {

    private final List<String> list;
    private int min = 999999;
    private final int before = 0;

    public PartUvIndices(List<String> list) {
        this.list = list;
    }

    public String calc(int before) {
        String erg = "uv_indices {\n";
        long size = list.stream().filter(str -> str.split(" ")[0].equalsIgnoreCase("f")).count();
        erg += size + ",\n";

        List<String> vertexList = list.stream().filter(str -> str.split(" ")[0].equalsIgnoreCase("f")).collect(Collectors.toList());
        List<IntVector> vectorList = new ArrayList<>();

        for (String line : vertexList) {
            String parts[] = line.split(" ");

            if (parts[1].split("/").length == 1 || parts[1].split("/")[1].isEmpty()) {
                return "";
            }

            if (parts[1].split("/").length == 2 || parts[1].split("/").length - 1 > 1) {

                String face1[] = parts[1].split("/");
                String face2[] = parts[2].split("/");
                String face3[] = parts[3].split("/");

                int x = Integer.valueOf(face1[1]);
                int y = Integer.valueOf(face2[1]);
                int z = Integer.valueOf(face3[1]);

                vectorList.add(new IntVector(x - 1 - before, y - 1 - before, z - 1 - before));
            } else {

                if (parts.length - 1 == 3) {
                    return "";
                }

                int x = Integer.valueOf(parts[1]);
                int y = Integer.valueOf(parts[2]);
                int z = Integer.valueOf(parts[3]);

                vectorList.add(new IntVector(x - 1 - before, y - 1 - before, z - 1 - before));
            }
        }

        for (int i = 0; i < vectorList.size(); i++) {
            IntVector intVector = vectorList.get(i);

            if (intVector.getX() < min) {
                min = intVector.getX();
            }
            if (intVector.getY() < min) {
                min = intVector.getY();
            }
            if (intVector.getZ() < min) {
                min = intVector.getZ();
            }

            erg += intVector.toString();
            if (i < vectorList.size() - 1) {
                erg += ",";
            }
            if ((i + 1) % 2 == 0) {
                erg += "\n";
            }

        }

        erg += "}\n";
        return erg;
    }

    public String getString() {
        calc(before);
        return calc(min);
    }
}
