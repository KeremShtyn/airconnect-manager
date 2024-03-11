package com.chainerist.blockchain.manager.util.response;

import com.chainerist.blockchain.manager.util.ChaineristUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import static com.chainerist.blockchain.manager.util.response.ResponseContants.*;

public class ChaineristGenerator {

    private ChaineristGenerator() {
    }

    public static <T> ChaineristResponse<T> generateSignResponse(T payload, Object... parametersWithOrderVersionReferenceIdExtras) {

        ChaineristResponse<T> tChaineristResponse;

        if (payload instanceof Collection) {
            tChaineristResponse = new ChaineristResponse.SignResponseBuilder<T>()
                    .withPayload(payload)
                    .build();
        } else if (payload instanceof Map) {
            Map<String, Object> resultMap = (Map) payload;
            tChaineristResponse = new ChaineristResponse.SignResponseBuilder<T>()
                    .withPayload((T) resultMap)
                    .build();

        } else {
            tChaineristResponse = new ChaineristResponse.SignResponseBuilder<T>()
                    .withPayload(payload)
                    .build();
        }


        if (parametersWithOrderVersionReferenceIdExtras.length > 0) {
            for (Object object : parametersWithOrderVersionReferenceIdExtras) {
             if (object instanceof String && object == parametersWithOrderVersionReferenceIdExtras[0]) {
                    tChaineristResponse.setVersion((String) object);
                } else if (object instanceof String && object == parametersWithOrderVersionReferenceIdExtras[1]) {
                    tChaineristResponse.setReferenceId((String) object);
                }
            }
        } else {
            tChaineristResponse.setVersion(SIGN_RESPONSE_VERSION);
            tChaineristResponse.setReferenceId(SIGN_RESPONSE_REFERENCE + ChaineristUtil.formatLocalDateTimeAsString(LocalDateTime.now(), SIGN_KEY_DATE_TIME_FORMAT));
        }

        return tChaineristResponse;
    }

    public static <T, S> Map<String, T> generatePageMap(List<T> objectResultList, Page<S> page) {
        Map<String, Object> pageMap = new HashMap<>();
        pageMap.put(PAGE_MAP_PARAMETER_RESULT, objectResultList);
        pageMap.put(PAGE_MAP_PARAMETER_PAGE, page.getPageable());
        return (Map<String, T>) pageMap;
    }

    public static Sort generateSorts(String[] sortBy) {
        return Sort.by(
                Arrays.stream(sortBy)
                        .map(sort -> sort.split(";", 2))
                        .map(array ->
                                new Sort.Order(generateDirection(array[1]), array[0])
                        ).collect(Collectors.toList())
        );
    }

    private static Sort.Direction generateDirection(String sortDirection) {
        if (sortDirection.equalsIgnoreCase("DESC")) {
            return Sort.Direction.DESC;
        } else {
            return Sort.Direction.ASC;
        }
    }


    public static Map<String, String> populateSort(String[] sortBy) {
        if (Objects.isNull(sortBy)) {
            return null;
        }
        Map<String, String> sort = new HashMap<>();
        try {
            Arrays.asList(sortBy).forEach(f -> {
                String[] sorted = f.split(":");
                sort.put(sorted[0], sorted[1]);
            });
        } catch (Exception e) {
            //TODO : handle exception
        }
        return sort;
    }
}
