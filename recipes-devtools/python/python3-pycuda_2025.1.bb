SUMMARY = "Python wrapper for Nvidia CUDA"
HOMEPAGE = "http://mathema.tician.de/software/pycuda"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=8dd9e67c46dbe605fba6aeb236b48c8a"

DEPENDS = "python3-setuptools-native python3-cython-native python3-cython python3-numpy-native cuda-profiler-api-native"

SRC_URI[sha256sum] = "52738e9a941c295c0a5df6aa0d49ac89f66d835dacce9c9c8b84e7530fe4622f"

COMPATIBLE_MACHINE = "(tegra)"

S = "${WORKDIR}/pycuda-${PV}"

inherit pypi cuda setuptools3

CUDA_PATH = "/usr/local/cuda-${CUDA_VERSION}"
CFLAGS += "-I=${CUDA_PATH}/include"

do_configure() {
    # special configururation
    ${PYTHON_PN} -u ${S}/configure.py
    # disable CURAND otherwise cannot compile
    sed -i 's?CUDA_ENABLE_CURAND = True?CUDA_ENABLE_CURAND = False?g' ${B}/siteconf.py
}

RDEPENDS:${PN} += "\
    python3-appdirs \
    python3-decorator \
    python3-mako \
    python3-pytools \
    python3-core \
    python3-crypt \
    python3-io \
    python3-math \
    python3-numbers \
    python3-numpy \
    python3-pkg-resources \
    python3-py \
    python3-six \
    cuda-nvcc \
"
