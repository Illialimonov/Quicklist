package com.ilusha.marketplaceAPI.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContactSellerDTO {
    private String recipientEmail;
    private String listingName;
    private String message;
}
