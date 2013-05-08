SUMMARY = "Driver for Realtek USB wireless devices"
HOMEPAGE = "http://www.realtek.com/"
LICENSE = "GPLv2"
PR = "r2"
LIC_FILES_CHKSUM = "file://os_dep/linux/os_intfs.c;endline=19;md5=f8d10a6bd2fdfa240c0634a7c660c57f"

SRC_URI = "file://rtl8188C_8192C_usb_linux_v3.4.4_4749.20121105.tar.gz"

S = "${WORKDIR}/rtl8188C_8192C_usb_linux_v3.4.4_4749.20121105"

inherit module siteinfo

EXTRA_OEMAKE = "CONFIG_RTL8192CU=m"

do_configure() {
        sed -e "s/^CONFIG_PLATFORM_I386_PC.*=.*y/EXTRA_CFLAGS += -DCONFIG_${@base_conditional('SITEINFO_ENDIANNESS', 'le', 'LITTLE', 'BIG', d)}_ENDIAN/" -i Makefile
}
do_compile() {
        unset CFLAGS CPPFLAGS CXXFLAGS LDFLAGS
        oe_runmake -C "${STAGING_KERNEL_DIR}" M="${S}" modules
}

do_install() {
        install -d ${D}/lib/modules/${KERNEL_VERSION}/kernel/drivers/net/wireless
        install -m 0644 ${S}/8192cu.ko ${D}${base_libdir}/modules/${KERNEL_VERSION}/kernel/drivers/net/wireless
}

