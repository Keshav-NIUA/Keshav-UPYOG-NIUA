package org.egov.bpa.web.model;

import java.util.List;
import java.util.Objects;

import javax.validation.Valid;

import org.egov.common.contract.response.ResponseInfo;
import org.springframework.validation.annotation.Validated;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Contains the ResponseHeader and the created/updated property.
 */
@ApiModel(description = "Contains the ResponseHeader and the created/updated property.")
@Validated
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class BPAResponse {

  /** Response information for the BPA response */
  private ResponseInfo responseInfo;

  /** List of BPA objects */
  @Valid
  private List<BPA> BPA;

  /** Count of BPA objects */
  private int count;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BPAResponse that = (BPAResponse) o;
    return count == that.count &&
            Objects.equals(responseInfo, that.responseInfo) &&
            Objects.equals(BPA, that.BPA);
  }

  @Override
  public int hashCode() {
    return Objects.hash(responseInfo, BPA, count);
  }

  @Override
  public String toString() {
    return "BPAResponse{" +
            "responseInfo=" + responseInfo +
            ", BPA=" + BPA +
            ", count=" + count +
            '}';
  }
}