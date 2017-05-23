package com.dtsgroup.labourlaw.parser;


import com.dtsgroup.labourlaw.model.JSonItemSubChapterLaw;
import com.dtsgroup.labourlaw.model.SubChapterLaw;

public class JSubChapterLaw {

    public static SubChapterLaw getSubChapterLaw(JSonItemSubChapterLaw jSonItemSubChapterLaw){
        SubChapterLaw subChapterLaw = new SubChapterLaw();

        subChapterLaw.setId(jSonItemSubChapterLaw.getId());
        subChapterLaw.setChapterDesEn(jSonItemSubChapterLaw.getChapterDesEn());
        subChapterLaw.setChapterDesVi(jSonItemSubChapterLaw.getChapterDesVi());
        subChapterLaw.setChapterNameEn(jSonItemSubChapterLaw.getChapterNameEn());
        subChapterLaw.setChapterNameVi(jSonItemSubChapterLaw.getChapterNameVi());
        subChapterLaw.setChapterTitleEn(jSonItemSubChapterLaw.getChapterTitleEn());
        subChapterLaw.setChapterTitleVi(jSonItemSubChapterLaw.getChapterTitleVi());
        subChapterLaw.setSubChapter(jSonItemSubChapterLaw.getSubChapter());

        return subChapterLaw;
    }
}
