package com.chainerist.blockchain.manager.airconnect.dto;


import com.chainerist.blockchain.manager.airconnect.util.SearchCriteria;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchDTO {

    private List<SearchCriteria> searchCriteriaList;
    private String dataOption;

}
