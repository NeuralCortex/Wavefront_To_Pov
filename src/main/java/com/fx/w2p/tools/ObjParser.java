package com.fx.w2p.tools;

import com.fx.w2p.parts.PartFaceIndices;
import com.fx.w2p.parts.PartNormalIndices;
import com.fx.w2p.parts.PartNormalVectors;
import com.fx.w2p.parts.PartUvIndices;
import com.fx.w2p.parts.PartUvVectors;
import com.fx.w2p.parts.PartVertexVectors;
import com.fx.w2p.pojo.ObjIndex;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 *
 * @author pscha
 */
public class ObjParser {

    private final List<ObjIndex> idxList = new ArrayList<>();
    private final LinkedHashMap<String, List<String>> objMap = new LinkedHashMap<>();
    private int max = 0;
    private int countObj = 0;

    public ObjParser(List<String> list) {
        getIndices(list);
        partitionList(list);

        for (String key : objMap.keySet()) {
            System.out.println(key);
            countObj++;
        }
    }

    private void getIndices(List<String> list) {
        for (int i = 0; i < list.size(); i++) {
            String line = list.get(i);
            if (line.startsWith("o")) {
                ObjIndex idx = new ObjIndex(i, line.split(" ")[1].toLowerCase());
                idxList.add(idx);
            }
        }
    }

    private void partitionList(List<String> list) {
        //0 bis vorletztes Objekt
        for (int i = 0; i < idxList.size() - 1; i++) {
            List<String> partList = new ArrayList<>();
            for (int j = 0; j < list.size(); j++) {

                ObjIndex objIndex = idxList.get(i);
                ObjIndex objIndexNext = idxList.get(i + 1);

                if (j > objIndex.getIdx() && j < objIndexNext.getIdx()) {
                    partList.add(list.get(j));
                }
            }
            objMap.put(idxList.get(i).getObjName(), partList);
        }

        //letztes Objekt
        int i = idxList.size() - 1;
        List<String> partList = new ArrayList<>();
        for (int j = 0; j < list.size(); j++) {

            ObjIndex objIndex = idxList.get(i);

            if (j > objIndex.getIdx()) {
                partList.add(list.get(j));
            }
        }
        objMap.put(idxList.get(i).getObjName(), partList);
    }

    public String buildScene() {
        String scene = "";

        List<String> keyList = new ArrayList<>(objMap.keySet());

        for (int i = 0; i < keyList.size(); i++) {
            String key = keyList.get(i);

            scene += "#declare " + key + " = union {\n";
            scene += parse(objMap.get(key));
            scene += "}\n\n";
        }

        return scene;
    }

    private String parse(List<String> list) {
        String mesh2 = "mesh2 {\n";

        PartVertexVectors partVertexVectors = new PartVertexVectors(list);
        mesh2 += partVertexVectors.getString();

        PartUvVectors partUvVectors = new PartUvVectors(list);
        mesh2 += partUvVectors.getString();

        PartNormalVectors partNormalVectors = new PartNormalVectors(list);
        mesh2 += partNormalVectors.getString();

        PartFaceIndices partFaceIndices = new PartFaceIndices(list, max);
        mesh2 += partFaceIndices.getString();
        max = partFaceIndices.getMax() + max + 1;

        PartUvIndices partUvIndices = new PartUvIndices(list);
        mesh2 += partUvIndices.getString();

        PartNormalIndices partNormalIndices = new PartNormalIndices(list);
        mesh2 += partNormalIndices.getString();

        mesh2 += "}\n";
        return mesh2;
    }

    public int getCountObj() {
        return countObj;
    }
}
