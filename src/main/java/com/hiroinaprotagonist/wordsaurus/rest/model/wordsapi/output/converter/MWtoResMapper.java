package com.hiroinaprotagonist.wordsaurus.rest.model.wordsapi.output.converter;

import com.hiroinaprotagonist.wordsaurus.rest.model.wordsapi.input.response.mwapi.MWThesaurusWord;
import com.hiroinaprotagonist.wordsaurus.rest.model.wordsapi.output.response.MWThesResWord;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MWtoResMapper {

    MWtoResMapper MAPPER = Mappers.getMapper(MWtoResMapper.class);
    @Mapping(source = "meta", target="meta")
    @Mapping(source = "meta.id", target = "meta_id")
    @Mapping(source = "meta.uuid", target = "meta_uuid")
    @Mapping(source = "meta.src", target = "meta_src")
    @Mapping(source = "meta.section", target = "meta_section")
    @Mapping(source = "meta.target", target = "mw_target")
    @Mapping(source = "fl", target = "part_of_speech")
    @Mapping(source = "shortDef", target = "short_def")

    @Mapping(source = "meta.stems", target = "stems")
    @Mapping(source = "meta.syns", target = "synonyms")
    @Mapping(source = "meta.ants", target = "antonyms")

    @Mapping(source = "meta.offensive", target = "offensive")
    @Mapping(source = "hwi", target = "hwi")
    public MWThesResWord mwToResMapper(MWThesaurusWord inResWord);
}
