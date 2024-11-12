package api;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class AddItemToCartDTO {
     Boolean created;
     String itemId;
}
