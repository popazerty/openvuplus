DESCIPTION = "S3 cold boot"
MAINTAINER = "vuplus team"
LICENSE = "CLOSED"


PV = "1.0"
PR = "r3"
SRC_REV = ""

SRC_URI = "file://coldboot \
	   file://coldboot.sh \
	   file://ethwol.sh \
"

do_install() {
	install -d ${D}/etc/init.d \
		${D}/usr/bin \
        	${D}${sysconfdir}/rc0.d 

	install -m 0755 ${WORKDIR}/coldboot.sh ${D}/etc/init.d/coldboot.sh
	install -m 0755 ${WORKDIR}/ethwol.sh ${D}/etc/init.d/ethwol.sh
	install -m 0755 ${WORKDIR}/coldboot ${D}/usr/bin/coldboot
        ln -sf   ../init.d/coldboot.sh ${D}${sysconfdir}/rc0.d/S50coldboot.sh
        ln -sf   ../init.d/ethwol.sh ${D}${sysconfdir}/rc0.d/K32ethwol.sh
}
