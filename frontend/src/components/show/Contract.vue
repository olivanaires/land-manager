<template>
    <div>
        <b-row align-h="center">
            <b-card class="col-md-11">
                <b-card-body>
                    <b-row>
                        <c-input-text v-model="contract.number" label-value="Número" bs-col-value="col-md-3"
                                      :disabled="true"/>
                        <c-input-date v-model="contract.contractdate" label-value="Data Assinatura"
                                      bs-col-value="col-md-3" :disabled="true"/>
                        <b-form-group label="Valor Total" class=" col-md-3">
                            <money v-model="contract.totalvalue" class="form-control" :disabled="true"></money>
                        </b-form-group>
                        <c-input-date v-model="contract.firstpaymentdate" label-value="Primeiro Pagamento"
                                      bs-col-value="col-md-3" :disabled="true"/>
                    </b-row>

                    <b-row>
                        <c-input-text v-model="contract.entranceparcelqtd" label-value="Parcelas Entrada"
                                      bs-col-value="col-md-3"
                                      :disabled="true"/>
                        <b-form-group label="Valor 1ª Entrada" class=" col-md-3">
                            <money v-model="contract.firstentrancepaymentvalue" class="form-control" :disabled="true"></money>
                        </b-form-group>
                        <b-form-group label="Valor Entrada" class=" col-md-3">
                            <money v-model="contract.entrancepaymentvalue" class="form-control" :disabled="true"></money>
                        </b-form-group>
                        <c-input-date v-model="contract.entrancepaymentdate" label-value="Data Entrada"
                                      bs-col-value="col-md-3"
                                      :disabled="true"/>
                    </b-row>

                    <b-row>
                        <c-input-text v-model="contract.intercalatedparcelqtd" label-value="Parcelamento Interc."
                                      bs-col-value="col-md-3"
                                      :disabled="true"/>
                        <c-input-text v-model="contract.intercalatedqtd" label-value="Qtd. Parc. Intercaladas"
                                      bs-col-value="col-md-3"
                                      :disabled="true"/>
                        <c-input-text v-model="contract.monthsqtdbetweeninter" label-value="Intervalo Interc.(Meses)"
                                      bs-col-value="col-md-3"
                                      :disabled="true"/>
                        <b-form-group label="Valor Intercalada" class=" col-md-3">
                            <money v-model="contract.intercalatedvalue" class="form-control" :disabled="true"></money>
                        </b-form-group>
                    </b-row>

                    <b-row>
                        <c-input-text v-model="contract.parcelqtd" label-value="Qtd. Parcelas" bs-col-value="col-md-2"
                                      :disabled="true"/>
                        <b-form-group label="Valor Parcelas" class=" col-md-3">
                            <money v-model="contract.parcelvalue" class="form-control" :disabled="true"></money>
                        </b-form-group>
                        <c-input-text v-model="contract.latepenalty" label-value="Multa Atraso(%)"
                                      bs-col-value="col-md-3"
                                      :disabled="true"/>
                        <c-input-text v-model="contract.monetarycorrection" label-value="Correção(%)"
                                      bs-col-value="col-md-2"
                                      :disabled="true"/>
                        <c-input-text v-model="contract.mora" label-value="Mora(%)" bs-col-value="col-md-2"
                                      :disabled="true"/>
                    </b-row>


                    <b-button v-b-modal.show-payments variant="primary">
                        Visualizar Pagamentos
                    </b-button>

                </b-card-body>
            </b-card>
        </b-row>

        <b-modal id="show-payments" title="Visualizar Pagamentos" centered size="xl" hide-footer scrollable>
            <c-payment-show :contract-id="contractId"/>
        </b-modal>
    </div>
</template>

<script>
    import ContractService from '../../services/contract.service';
    import CPaymentShow from "./Payment";

    export default {
        name: 'c-contract-show',
        components: {CPaymentShow},
        props: {
            contractId: {required: true}
        },
        data() {
            return {
                contract: {},
                undefinedValue: 'Indefinido',
                filesToShow: [],
                payments: [],
            }
        },
        mounted() {
            ContractService.load(this.contractId).then(response => {
                this.contract = response.data;
            }).catch(error => this.$swal({icon: 'error', title: error.response.data.message}));
        },
    }

</script>