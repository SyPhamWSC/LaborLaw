package com.dtsgroup.labourlaw.parser;


import com.dtsgroup.labourlaw.model.ChapterLaw;
import com.dtsgroup.labourlaw.model.JSonChapterLaw;

public class JChapterLaw {

    public static ChapterLaw getChapterLaw(JSonChapterLaw jSonChapterLaw){
        ChapterLaw chapterLawTemp = new ChapterLaw();
        chapterLawTemp.setId(jSonChapterLaw.getId());
        chapterLawTemp.setType_chapter(jSonChapterLaw.getTypeChapter());
        chapterLawTemp.setSname_chapter_vi(jSonChapterLaw.getSnameChapterVi());
        chapterLawTemp.setSname_chapter_en(jSonChapterLaw.getSnameChapterEn());
        chapterLawTemp.setDescription_en(jSonChapterLaw.getDescriptionEn());
        chapterLawTemp.setDescription_vi(jSonChapterLaw.getDescriptionVi());
        chapterLawTemp.setNum_chapter(jSonChapterLaw.getNumChapter());
        return chapterLawTemp;
    }
}
