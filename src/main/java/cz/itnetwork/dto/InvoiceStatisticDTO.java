package cz.itnetwork.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class InvoiceStatisticDTO {
    private Long currentYearSum;

    private Long allTimeSum;

    private Long invoicesCount;


}
