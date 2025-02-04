package org.prebid.server.functional.model.mock.services.vendorlist

import org.prebid.server.functional.util.PBSUtils
import org.prebid.server.functional.util.privacy.TcfConsent

import java.time.Clock
import java.time.ZonedDateTime

import static org.prebid.server.functional.util.privacy.TcfConsent.GENERIC_VENDOR_ID

class VendorListResponse {

    Integer gvlSpecificationVersion
    Integer vendorListVersion
    Integer tcfPolicyVersion
    ZonedDateTime lastUpdated
    Map<Integer, Vendor> vendors

    static VendorListResponse getDefaultVendorListResponse() {
        new VendorListResponse().tap {
            it.gvlSpecificationVersion = 2
            it.tcfPolicyVersion = 2
            it.lastUpdated = ZonedDateTime.now(Clock.systemUTC()).minusWeeks(2)
            it.vendors = [(GENERIC_VENDOR_ID): Vendor.defaultVendor]
        }
    }

    static class Vendor {

        Integer id
        String name
        List<Integer> purposes
        List<Integer> legIntPurposes
        List<Integer> flexiblePurposes
        List<Integer> specialPurposes
        List<Integer> features
        List<Integer> specialFeatures
        String policyUrl
        Overflow overflow
        String cookieMaxAgeSeconds
        Boolean usesCookies
        Boolean cookieRefresh
        Boolean usesNonCookieAccess
        Boolean deviceStorageDisclosureUrl

        static Vendor getDefaultVendor() {
            new Vendor().tap {
                it.id = GENERIC_VENDOR_ID
                it.name = PBSUtils.randomString
                it.purposes = [1, 3, 4, 5]
                it.legIntPurposes = [2, 7, 10]
                it.flexiblePurposes = [2, 7, 10]
                it.specialPurposes = [1, 2]
                it.features = [2, 3]
                it.specialFeatures = [1]
                it.policyUrl = "https://www.any.policy.com"
                it.overflow = new Overflow(httpGetLimit: PBSUtils.randomNumber)
                it.cookieMaxAgeSeconds = 31536000
                it.usesCookies = true
                it.cookieRefresh = false
                it.usesNonCookieAccess = true
                it.deviceStorageDisclosureUrl = "https://www.storage.disclousure.com"
            }
        }

        static class Overflow {

            Integer httpGetLimit
        }
    }
}
