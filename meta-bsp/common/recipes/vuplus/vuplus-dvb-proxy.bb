DESCRIPTION = "Hardware drivers for VuPlus"
SECTION = "base"
LICENSE = "CLOSED"

KV = "${VUPLUS_KERNEL_VERSION}"
PV = "${KV}"
PR = "${SRCDATE}.${SRCDATE_PR}"

SRC_URI = "http://archive.vuplus.com/download/build_support/vuplus/vuplus-dvb-proxy-${MACHINE}-${PV}-${PR}.tar.gz "

DEPENDS += "virtual/kernel module-init-tools"
RDEPENDS_${PN} += "module-init-tools-depmod"

S = "${WORKDIR}"

inherit module-base

do_install() {
        install -d ${D}/lib/modules/${KERNEL_VERSION}/extra
        for f in *.ko; do
                install -m 0644 ${WORKDIR}/$f ${D}/lib/modules/${KERNEL_VERSION}/extra/$f;
        done
}

pkg_postinst_${PN} () {
        if [ -d /proc/stb ]; then
                depmod -a
        fi
        true
}

PACKAGE_ARCH := "${MACHINE_ARCH}"
FILES_${PN} = "/"
