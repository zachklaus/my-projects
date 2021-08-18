################################################################################
# Automatically-generated file. Do not edit!
################################################################################

# Add inputs and outputs from these tool invocations to the build variables 
CC_SRCS += \
../hw1.cc \
../hw2.cc \
../hw3.cc \
../hw4.cc \
../hw5.cc \
../hw6.cc \
../hw7.cc \
../test.cc 

CC_DEPS += \
./hw1.d \
./hw2.d \
./hw3.d \
./hw4.d \
./hw5.d \
./hw6.d \
./hw7.d \
./test.d 

OBJS += \
./hw1.o \
./hw2.o \
./hw3.o \
./hw4.o \
./hw5.o \
./hw6.o \
./hw7.o \
./test.o 


# Each subdirectory must supply rules for building sources it contributes
%.o: ../%.cc
	@echo 'Building file: $<'
	@echo 'Invoking: Cygwin C++ Compiler'
	g++ -O0 -g3 -Wall -c -fmessage-length=0 -MMD -MP -MF"$(@:%.o=%.d)" -MT"$(@)" -o "$@" "$<"
	@echo 'Finished building: $<'
	@echo ' '


