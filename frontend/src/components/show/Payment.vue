<template>
    <div>
        <b-row align-h="center">
            <b-card class="col-md-12">
                <b-card-body>
                    <b-row>
                        <b-form-group label="" class="col-md-12">
                            <b-table :items="payments"
                                     :fields="paymentFields"
                                     show-empty empty-text="Sem pagamentos">

                                <template v-slot:cell(description)="data">
                                    {{ `${data.item.description == 'INTERCALET' ? 'Intercalada' : 'Parcela'} Nº
                                    ${data.item.number}` }}
                                </template>

                                <template v-slot:cell(expirationdate)="data">
                                    {{ formatDate(data.item.expirationdate) }}
                                </template>

                                <template v-slot:cell(paydate)="data">
                                    {{ data.item.paydate ? formatDate(data.item.paydate) : 'Atrasado ' + data.item.latedays + ' dias' }}
                                </template>

                                <template v-slot:cell(value)="data">
                                    {{ formatCurrency(data.item.value) }}
                                </template>

                                <template v-slot:cell(paidvalue)="data">
                                    {{ data.item.paidvalue ? formatCurrency(data.item.paidvalue) : '' }}
                                </template>

                                <template v-slot:cell(executed)="data">
                                    {{ data.item.executed ? 'Sim' : 'Não'}}
                                </template>

                                <template v-slot:cell(options)="data">
                                    <b-link v-b-modal.show-payment v-on:click="show(data.item)"
                                            v-if="!data.item.executed" class="option-item"
                                            v-b-tooltip.hover title="Receber Pagamento">
                                        <b-icon icon="check-square"></b-icon>
                                    </b-link>
                                </template>
                            </b-table>
                        </b-form-group>
                    </b-row>
                </b-card-body>
            </b-card>
        </b-row>

        <b-modal id="show-payment" title="Confirmar Pagamento" centered size="xl" hide-footer scrollable>
            <b-row align-h="center">
                <b-card class="col-md-11">
                    <b-card-body>
                        <b-row>
                            <c-input-text v-model="payment.descriptionLabel" label-value="Tipo" bs-col-value="col-md-3"
                                          :disabled="true"/>
                            <c-input-text v-model="payment.number" label-value="Número" bs-col-value="col-md-3"
                                          :disabled="true"/>
                            <c-input-date v-model="payment.expirationdate" label-value="Vencimento"
                                          bs-col-value="col-md-3"
                                          :disabled="true"/>
                            <c-input-text v-model="payment.executedLabel" label-value="Pago?" bs-col-value="col-md-3"
                                          :disabled="true"/>
                        </b-row>
                        <b-row>
                            <b-form-group label="Valor" class="col-md-3">
                                <money v-model="payment.value" class="form-control" :disabled="true"></money>
                            </b-form-group>
                            <c-input-text v-model="payment.insurance" label-value="Multa (%)" bs-col-value="col-md-3"
                                          :disabled="true"/>
                            <c-input-text v-model="payment.latedaysLabel" label-value="Atraso (Dias)" bs-col-value="col-md-3"
                                          :disabled="true"/>
                            <b-form-group label="Valor Multa (Dia)" class="col-md-3">
                                <money v-model="payment.interestDay" class="form-control" :disabled="true"></money>
                            </b-form-group>
                        </b-row>

                        <b-row>
                            <b-form-group label="Valor com Multa" class="col-md-3">
                                <money v-model="payment.valueWithInsurance" class="form-control" :disabled="true"></money>
                            </b-form-group>
                            <b-form-group label="Disconto (R$)" class="col-md-3">
                                <money v-model="payment.discount" class="form-control" v-on:input="changeDiscount($event)" ></money>
                            </b-form-group>
                            <b-form-group label="Valor Final" class="col-md-3">
                                <money v-model="payment.paidvalue" class="form-control"></money>
                            </b-form-group>
                            <b-button v-on:click="pay()" variant="primary" class="col-md-3"
                                      style="height: 40px; margin-top: 30px;">
                                Baixa Pagamento
                            </b-button>
                        </b-row>
                    </b-card-body>
                </b-card>
            </b-row>
        </b-modal>
    </div>
</template>

<script>
    import moment from 'moment';
    import PaymentService from '../../services/payment.service';

    export default {
        name: 'c-payment-show',
        props: {
            contractId: {required: true},
        },
        data() {
            return {
                payment: {},
                payments: [],
                paymentValue: 0.00,
                paymentFields: [
                    {
                        key: 'description',
                        label: 'Descrição',
                    },
                    {
                        key: 'expirationdate',
                        label: 'Data Vencimento',
                        formatter: "formatDate"
                    },
                    {
                        key: 'paydate',
                        label: 'Data Pagamento',
                        formatter: "formatDate"
                    },
                    {
                        key: 'value',
                        label: 'Valor',
                        formatter: "formatCurrency"
                    },
                    {
                        key: 'paidvalue',
                        label: 'Valor Pago',
                        formatter: "formatCurrency"
                    },
                    {
                        key: 'executed',
                        label: 'Pago?',
                    },
                    {
                        key: 'options',
                        label: 'Opções',
                        tdClass: 'center',
                        thClass: 'center'
                    }
                ],
            }
        },
        mounted() {
            PaymentService.byContract(this.contractId)
                .then(result => {
                    this.payments = result.data;
                }).catch(error => this.$swal({icon: 'error', title: error.response.data.message}));
        },
        methods: {
            formatDate(value) {
                return moment(String(value), "YYYY-MM-DD").format('DD/MM/YYYY');
            },
            formatCurrency(value) {
                let formatter = new Intl.NumberFormat('pt-BR', {
                    style: 'currency',
                    currency: 'BRL',
                });
                return formatter.format(value);
            },
            changeDiscount(newValue) {
                this.payment.paidvalue = this.paymentValue - newValue;
            },
            show(payment) {
                const interestDay = payment.value * (payment.contract.latepenalty / 100);
                const valueWithInsurance = payment.value + (interestDay * payment.latedays);

                this.payment = payment;
                this.payment['descriptionLabel'] = payment.description == 'INTERCALET' ? 'Intercalada' : 'Parcela';
                this.payment['executedLabel'] = payment.executed ? 'Sim' : 'Não';
                this.payment['latedaysLabel'] = `${payment.latedays} Dias`;
                this.payment['insurance'] = `${payment.contract.latepenalty}%`;
                this.payment['interestDay'] = interestDay;
                this.payment['valueWithInsurance'] = valueWithInsurance;
                this.payment['paidvalue'] = valueWithInsurance;
                this.paymentValue = valueWithInsurance;
            },
            pay() {
                console.log(this.payment);
            }
        }
    }

</script>